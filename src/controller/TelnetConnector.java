package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import exception.TelnetException;

public class TelnetConnector {
	
	private Socket sock = null;
	private BufferedReader in = null;
	private PrintWriter out = null;
	
	public TelnetConnector(String ip, int port, String password) throws TelnetException {
		try {
			sock = new Socket(ip, port);
			
			in  = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			out = new PrintWriter(sock.getOutputStream());
			
			out.println(password);
			out.flush();
			
		} catch (Exception e) {
			throw new TelnetException("Error connecting to " + ip + ":" + port + "!");
		}
	}
	
	public void sendCommand(String command) throws TelnetException{
		try {
			out.println(command);
			out.flush();
		} catch (Exception e) {
			throw new TelnetException("Error sending command to " + sock.getLocalAddress() + ":" + sock.getPort() + "!");
		}
	}
	
	public void close() throws TelnetException{
		try {
			in.close();
			out.close();
			sock.close();
		} catch (Exception e) {
			throw new TelnetException("Error while closing the connection to " + sock.getLocalAddress() + ":" + sock.getPort() + "!");
		}
	}
}