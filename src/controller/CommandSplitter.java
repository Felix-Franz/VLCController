package controller;

import java.util.ArrayList;

import exception.TelnetException;

public class CommandSplitter {
	private ArrayList<TelnetConnector> server = new ArrayList<>();
	
	public void addServer(TelnetConnector connector){
		server.add(connector);
	}
	
	public void runCommand(String command){
		if (command.equals("exit")){
			for (TelnetConnector connector: server){
				try {
					connector.close();
				} catch (TelnetException e) {
					// TODO Auto-generated catch block
				}
			}
		}
		for (TelnetConnector connector: server){
			try {
				connector.sendCommand(command);
			} catch (TelnetException e) {
				System.out.println("Error sending command to one of the server!");
				// TODO Auto-generated catch block
			}
		}
	}
}
