package controller;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import exception.*;

public class ConfigReader {
	
	private ObjectProvider provider;
	
	public ConfigReader(ObjectProvider provider) {
		this.provider = provider;
	}
	
	public void readConfig(){
		try {
			System.out.println("Reading config...");
			BufferedReader configReader = new BufferedReader(new FileReader(provider.getConfPath() + "/server.conf"));
			String aktServer = configReader.readLine();
			while(aktServer != null){
				
				extractOutput(aktServer);
				aktServer = configReader.readLine();
				
			}
			configReader.close();
			System.out.println("Config read & Connected to all servers!");
			
		} catch (FileNotFoundException e) {
			System.out.println("Config not found!");
		} catch (IOException e) {
			System.out.println("Cannot read config!");
		} catch (TelnetException e) {
			System.out.println("Cannot connect to one of the server!");
			System.out.println(e.getMessage());
		}
	}

	private void extractOutput(String aktServer) throws TelnetException {
		aktServer = aktServer.replaceAll("\\s+", "");
		
		if(aktServer.indexOf('#')!=-1)
			aktServer=aktServer.substring(0, aktServer.indexOf('#')); 	// split config
		
		int esc = aktServer.indexOf('=');
		if (esc ==-1) return;
		String key = aktServer.substring(0, esc);
		String value = aktServer.substring(esc+1);
		
		handleOutput(key, value);
	}

	private void handleOutput(String key, String value) throws TelnetException {
		if (key.equals("vlc"))
			handleOutputAddServer(value);
		else if (key.equals("web-autostart")){
			provider.getWebConf().setAutoStart(value.equals("1"));
		}
		else if (key.equals("web-port"))
			provider.getWebConf().setPort(Integer.valueOf(value));
		else if (key.equals("web-maxConnections"))
			provider.getWebConf().setMaxConnections(Integer.valueOf(value));
	}

	private void handleOutputAddServer(String output) throws TelnetException {
		int esc1 = output.indexOf('~'); //Escape sign one
		int esc2 = output.indexOf('~', esc1+1);
		if (esc2!=-1) {
			String ip = output.substring(0, esc1);
			int port = Integer.valueOf(output.substring(esc1+1, esc2));
			String password = output.substring(esc2+1);
			TelnetConnector connector = new TelnetConnector(ip, port, password);
			provider.getSplitter().addServer(connector);
			System.out.println("Server " + output.substring(0, esc1) + " added!");
		}
	}
}
