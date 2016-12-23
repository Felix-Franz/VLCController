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
import java.util.HashMap;

import exception.WebServerException;

public class WebServer implements Runnable {
	controller.ObjectProvider provider;
	int overloadTime = 1000;	// in Milisekunden
	String webpage="<html><h1>ERROR 500 - internal server error</h1></html>";
	HashMap<String, String> additionalWebContend;
	ArrayList<Thread> threads = new ArrayList<>();
	ServerSocket ss;
	boolean enabled = false;
	String httpResponse = "HTTP/1.1 200 OK\r\n\r\n";
	
	public WebServer(controller.ObjectProvider provider){
		this.provider = provider;
		additionalWebContend = new HashMap<>();
	}
	
	public void autostart(){
		if (provider.getWebConf().isAutoStart())
			start();
	}
	
	public void init() throws WebServerException{
		
		if (provider.getWebConf().getPort() == 0 && provider.getWebConf().getMaxConnections() == 0)
			throw new WebServerException();
		webpage = readFile(provider.getConfPath() + "/webpage/index.html");
		
	}
	
	private String readFile(String path) throws WebServerException{
		try {
			BufferedReader br = new BufferedReader(new FileReader(path));
			String file = "";
			String line = br.readLine();
			while (line != null){
				file+=line + "\n";
				line = br.readLine();
			}
			
			br.close();
			return file;
		} catch (FileNotFoundException e) {
			System.out.println("Webserver: Didn't find " + path);
			// TODO Auto-generated catch block
			//e.printStackTrace();
			return "";
			//throw new WebServerException();
		} catch (IOException e) {
			System.out.println("Cannot read webpage");
			// TODO Auto-generated catch block
			//e.printStackTrace();
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
				webpage = "<html><h1>ERROR 500 - internal server error</h1></html>";
				additionalWebContend.clear();
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
			String out = HandleOutput(input, osw);
			while (input!=null && out == null) {
				out=HandleOutput(input, osw);
				input=isr.readLine();
			}
			
			if (out != null && out != "")
				osw.write(httpResponse + out);
			else
				osw.write(httpResponse +  "<html><h1>ERROR 404 - not found!</h1></html>");
			osw.flush();
			
			isr.close();
			osw.close();
			s.close();
		} catch (IOException e) {
			stop();
			System.out.println("Webserver Streaming ERROR");
		}
	}

	private String HandleOutput(String input, OutputStreamWriter output) throws IOException {
		if (input.contains("GET ")){
			input = input.substring(input.indexOf("GET ") + 4, input.indexOf("HTTP") -1);
			if (input.startsWith("/controle-")){
				input = input.substring("controle-".length() + 1);
				provider.getHandler().handle(input);
				return webpage;
			} else if(input.equals("/") || input.startsWith("//")){
				return  webpage;
			} else{
				return getAdditionalWebContend(input);
			}
		}
		return null;
	}
	
	private String getAdditionalWebContend(String input){
		if (additionalWebContend.containsKey(input)){
			return additionalWebContend.get(input);
		} else {
			try {
				additionalWebContend.put(input, readFile(provider.getConfPath() + "/webpage" + input));
				return additionalWebContend.get(input);
			} catch (WebServerException e) {
				System.out.println("Can't read file " + provider.getConfPath() + input);
				return "";
				// TODO Auto-generated catch block
			}
		}
	}

}
