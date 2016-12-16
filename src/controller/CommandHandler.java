package controller;

public class CommandHandler{
	ObjectProvider provider;
	
	public CommandHandler(ObjectProvider provider){
		this.provider = provider;
	}
	
	public void handle(String command){
		try {
			switch(command){
			case "fullscreen":
				System.out.println("Toggle fullscreen...");
				provider.getSplitter().runCommand("fullscreen");
				break;
			case "loop":
				System.out.println("Toggle loop...");
				provider.getSplitter().runCommand("loop");
				break;
			case "next":
				System.out.println("Starting the next song in the playlist...");
				provider.getSplitter().runCommand("next");
				break;
			case "pause":
				System.out.println("Toggle Pause...");
				provider.getSplitter().runCommand("pause");
				break;
			case "play":			
				System.out.println("Starting playlist...");
				provider.getSplitter().runCommand("play");
				break;
			case "prev":
				System.out.println("Play previous song...");
				provider.getSplitter().runCommand("prev");
				break;
			case "shuffle":
				System.out.println("Toggle shuffle...");
				provider.getSplitter().runCommand("random");
				break;
			case "stop":			
					System.out.println("Stopping playlist...");
					provider.getSplitter().runCommand("stop");
					break;
			case "web start":
				provider.getWeb().start();
				break;
			case "web stop":
				provider.getWeb().stop();
				break;
			case "exit":
				provider.getSplitter().runCommand("exit");
				System.out.println("Program closed!");
				System.exit(0);
				break;
			case "help":
				System.out.println("Just put in one of following commands!\n");
				System.out.println("exit\t\t Stops this program");
				System.out.println("help\t\t You should know what that does. You've already used it ;)");
				System.out.println("fullscreen\t Toggles fullscreen");
				System.out.println("loop\t\t Toggles the loop setting on vlc");
				System.out.println("next\t\t Plays the next song");
				System.out.println("pause\t\t Pause/Resumes the current running playlist");
				System.out.println("play\t\t starts the playlist");
				System.out.println("prev\t\t plays previous song");
				System.out.println("shuffle\t\t Toggles shuffle");
				System.out.println("stop\t\t Stops the current playlist");
				System.out.println("web start\t starts the webserver");
				System.out.println("web stop\t stops the web server");
				System.out.println("");
				break;
			default:
				System.out.println("Wrong input! " + command + " doesn't exist! Try 'help'");
			}
		} catch (Exception e) {
			System.out.println("Something went wrong!");
			// TODO: handle exception
		}
	}
}
