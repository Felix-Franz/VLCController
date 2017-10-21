package backend.general.connector.specialConnectors;

import backend.general.connector.AbstractConnector;
import backend.general.connector.enums.Command;
import backend.general.connector.enums.PlayerState;
import backend.utils.StringCutter;

import java.io.*;
import java.net.Socket;

/**
 * Created by Felix on 21.10.2017.
 */
public class VLCConnector implements AbstractConnector {

    private String name;
    private String host;
    private int port;
    private String password;

    private Socket connection = null;
    private BufferedReader in = null;
    private PrintWriter out = null;

    public VLCConnector(String name, String host, int port, String password){
        this.name = name;
        this.host = host;
        this.port = port;
        this.password = password;
    }

    @Override
    public boolean connect(){
        try {
            connection = new Socket(host, port);
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(connection.getOutputStream()));
            in.readLine();
            out.println(password);
            out.flush();
            in.readLine();
            if (in.readLine().equals("Welcome, Master")) return true;
        } catch (IOException e) {
            e.printStackTrace();    //ToDo handle exception
        }
        return false;
    }

    @Override
    public boolean runCommand(Command command){
        if (command == Command.PAUSE && getState() == PlayerState.PAUSED) return true;
        try {
            out.println(command.getCommand());
            out.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public String getTitle() {
        String title =  new StringCutter(getRawState()).cut("( new input: ", " )");
        if (title == null) return "no title";
        return title;
    }

    @Override
    public int getVolume() {
        try {
            return Integer.valueOf(new StringCutter(getRawState()).cut("( audio volume: ", " )"));
        } catch (NumberFormatException e){
            e.printStackTrace();    //ToDo handle Exception
            return -1;
        }
    }

    @Override
    public PlayerState getState() {
        String rawState = new StringCutter(getRawState()).cut("( state ", " )");
        if (rawState == null) return PlayerState.UNDEFINED;
        switch (rawState){
            case "playing":
                return PlayerState.PLAYING;
            case "paused":
                return PlayerState.PAUSED;
            case "stopped":
                return PlayerState.STOPPED;
            default:
                return PlayerState.UNDEFINED;
        }
    }

    private String getRawState(){
        String output = "";
        try {
            out.println("status");
            out.flush();
            output+=in.readLine();
            output+=in.readLine();
            if (output.contains("new input"))       //if nothing is in playlist this line is missing
                output+=in.readLine();
        } catch (Exception e) {
            e.printStackTrace();    //ToDo handle exception
        }
        return output;
    }
}
