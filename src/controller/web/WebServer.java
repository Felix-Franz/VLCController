package controller.web;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import exception.WebServerException;

public class WebServer implements Runnable {
	controller.ObjectProvider provider;
	int overloadTime = 1000;	// in Milisekunden
	String webpage="<html><h1>ERROR 500 - internal server error</h1></html>";
	ArrayList<Thread> threads = new ArrayList<>();
	ServerSocket ss;
	boolean enabled = false;
	
	public WebServer(controller.ObjectProvider provider){
		this.provider = provider;
	}
	
	public void autostart(){
		if (provider.getWebConf().isAutoStart())
			start();
	}
	
	public void init() throws WebServerException{
		
		if (provider.getWebConf().getPort() == 0 && provider.getWebConf().getMaxConnections() == 0)
			throw new WebServerException();
		readWebpage();
		
	}
	
	private void readWebpage() throws WebServerException{
		try {
			BufferedReader br = new BufferedReader(new FileReader(provider.getConfPath() + "/webpage.html"));
			webpage = "";
			String line = br.readLine();
			while (line != null){
				webpage+=line + "\n";
				line = br.readLine();
			}
			
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("Didn't find webpage");
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebServerException();
		} catch (IOException e) {
			System.out.println("Cannot read webpage");
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new WebServerException();
		}
	}
	
	public void start(){
		if (!enabled){
			try {
				init();
				
				ss = new ServerSocket(provider.getWebConf().getPort());
				startNewThread();
				System.out.println("Starting webserver...");
				enabled=true;
			} catch (IOException e) {
				System.out.println("Error starting webserver, is the port already used?");
			} catch (WebServerException e){
				
			}
		}
		else {
			System.out.println("Webserver already running!");
		}
	}

	private void startNewThread() {
		Thread th = new Thread(this);
		threads.add(th);
		th.start();
	}
	
	public void stop(){
		if (enabled){
			enabled=false;
			try {
				System.out.println("Stopping webserver...");				
				ss.close();
			} catch (IOException e) {
				System.out.println("Error stopping webserver");
			}
		}
		else
			System.out.println("Webserver not running!");
	}
	
	@Override
	public void run() {
		try{
			while (threads.size()>=provider.getWebConf().getMaxConnections()) java.lang.Thread.currentThread().wait(overloadTime);
			Socket s = ss.accept();
			startNewThread();
			streamWebsite(s);
			threads.remove(java.lang.Thread.currentThread());
		} catch (Exception e){
			if (!e.getMessage().contains("socket closed")){
				stop();
				System.out.println("Webserver ERROR");
			}
		}
	}
	
	private void streamWebsite(Socket s){
		try {
			OutputStreamWriter osw = new OutputStreamWriter(s.getOutputStream());
			BufferedReader isr = new BufferedReader(new InputStreamReader(s.getInputStream()));
			
			String input=isr.readLine();
			boolean run=true;
			while (input!=null && run) {
				run=HandleOutput(input);
				input=isr.readLine();
			}
			
			osw.write(webpage);
			osw.flush();
			
			isr.close();
			osw.close();
			s.close();
		} catch (IOException e) {
			stop();
			System.out.println("Webserver Streaming ERROR");
		}
	}

	private boolean HandleOutput(String input) {
		if (input.contains("GET ")){
			input = input.substring(input.indexOf("GET /") + 5, input.indexOf("HTTP") -1);
			if (input.startsWith("controle/")){
				input = input.substring("controle/".length());
				System.out.println("web: " + input);
				provider.getHandler().handle(input);
			}
			return false;
		}
		return true;
	}
}
