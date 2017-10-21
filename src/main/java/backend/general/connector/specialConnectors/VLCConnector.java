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

    private void connectIfNotConnected(){
        if (connection==null) connect();
    }

    @Override
    public boolean runCommand(Command command){
        connectIfNotConnected();
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
        return new StringCutter(getRawState()).cut("( new input: ", " )");
    }

    @Override
    public int getVolume() {
        return Integer.valueOf(new StringCutter(getRawState()).cut("( audio volume: ", " )"));
    }

    @Override
    public PlayerState getState() {
        String rawState = new StringCutter(getRawState()).cut("( state ", " )");
        switch (rawState){
            case "playing":
                return PlayerState.PLAYING;
            case "paused":
                return PlayerState.PAUSED;
            default:
                return null;
        }
    }

    private String getRawState(){
        connectIfNotConnected();
        String output = "";
        try {
            out.println("status");
            out.flush();
            output+=in.readLine();
            output+=in.readLine();
            output+=in.readLine();
        } catch (Exception e) {
            e.printStackTrace();    //ToDo handle exception
        }
        return output;
    }
}
