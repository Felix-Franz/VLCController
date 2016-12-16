package controller;

//TODO Beautify webpage.html
//TODO Handle Exceptions
//TODO Refactor

public class Main {
	
		public static void main(String[] args) {
		try {
			System.out.println("Starting...");
			
			ObjectProvider provider;
			if (args.length <1 || args[0]== null)
				provider = new ObjectProvider("conf");
			else
				provider = new ObjectProvider(args[0]);
						
			System.out.println("Welcome to VLC Controller (© Felix Franz), type 'help' if don't know what you can do here.\n");
			
			
			while(true){
				provider.getHandler().handle(provider.getCommandlineReader().read());
			}
			
			
		} catch (Exception e) {
			System.out.println("Something went wrong!");
			// TODO: handle exception
		}
	}
}
