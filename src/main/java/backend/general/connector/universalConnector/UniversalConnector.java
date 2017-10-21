package backend.general.connector.universalConnector;

import java.io.*;
import java.net.Socket;

/**
 * Created by Felix on 09.10.2017.
 */
public class UniversalConnector {
    private String name = "LocalVLC";
    private String host = "127.0.0.1";
    private int port = 4212;
    private String password = "pass";

    private Socket connection = null;
    private BufferedReader in = null;
    private PrintWriter out = null;


    protected UniversalConnector(){
    }

    /**
     * Connects to a UniversalConnector instance
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
     * Runs a command on this UniversalConnector instance
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
     * @return Name of UniversalConnector instance
     */
    public String getName(){
        return name;
    }

    /**
     *
     * @return host of UniversalConnector instance
     */
    public String getHost() {
        return host;
    }

    /**
     *
     * @return port of universalConnector instance
     */
    public int getPort() {
        return port;
    }
}
