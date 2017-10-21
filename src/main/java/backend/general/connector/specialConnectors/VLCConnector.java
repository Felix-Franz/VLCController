package backend.general.connector.specialConnectors;

import backend.general.connector.AbstractConnector;
import backend.general.connector.enums.Command;

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
        try {
            out.println(command.getCommand());
            out.flush();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;

    }

}
