package backend.general.vlc;

import java.io.*;
import java.net.Socket;

/**
 * Created by Felix on 09.10.2017.
 */
public class VLC {
    private String name;
    private String host;
    private int port;
    private String password;

    private Socket connection = null;
    private BufferedReader in = null;
    private PrintWriter out = null;


    protected VLC(){
    }

    /**
     * Connects to a VLC instance
     *
     * @return true: connection established, false: any exception
     */
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

    /**
     * Runs a command on this VLC instance
     *
     * @param command
     * @return true: command was successful, false: any exception
     */
    public boolean runCommand(Command command){
        try {
            out.println(command.getCommand());
            out.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

    /**
     *
     * @return Name of VLC instance
     */
    public String getName(){
        return name;
    }
}
