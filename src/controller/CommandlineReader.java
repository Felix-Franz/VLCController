package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandlineReader {
	private BufferedReader in = null;
	
	public CommandlineReader() {
		in = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public String read(){
		String input;
		try {
			input = in.readLine();
		} catch (Exception e) {
			input = "help";
		}
		return input;
	}
}
