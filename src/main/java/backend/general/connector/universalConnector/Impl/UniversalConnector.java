package backend.general.connector.universalConnector.Impl;

import backend.general.connector.enums.Command;
import backend.general.connector.enums.PlayerState;
import backend.general.connector.specialConnectors.AbstractSpecialConnector;
import backend.general.connector.specialConnectors.VLCConnector;

/**
 * Created by Felix on 09.10.2017.
 */
public class UniversalConnector implements backend.general.connector.universalConnector.UniversalConnector {
    private String name = "LocalVLC";
    private String host = "127.0.0.1";
    private int port = 4212;
    private String password = "pass";

    private transient AbstractSpecialConnector connector;
    private transient boolean firstRun = true;

    protected UniversalConnector(){
    }

    /**
     * Creates a new instance of a connector at the first run
     */
    private void createInstanceOnFirstRun(){
        if (firstRun){
            connector = new VLCConnector(name, host, port, password);
            firstRun = false;
        }
    }

    @Override
    public boolean connect(){
       createInstanceOnFirstRun();
       return connector.connect();
    }

    @Override
    public void disconnect() {
        createInstanceOnFirstRun();
        connector.disconnect();
    }

    @Override
    public boolean runCommand(Command command){
        createInstanceOnFirstRun();
        return connector.runCommand(command);
    }

    @Override
    public String getTitle() {
        createInstanceOnFirstRun();
        return connector.getTitle();
    }

    @Override
    public int getVolume() {
        createInstanceOnFirstRun();
        return connector.getVolume();
    }

    @Override
    public PlayerState getState() {
        createInstanceOnFirstRun();
        return connector.getState();
    }

    @Override
    public void setVolume(int volume) {
        connector.setVolume(volume);
    }

    @Override
    public long getResponseTime(){
        long start = System.currentTimeMillis();
        if (connector.getState() == PlayerState.UNDEFINED) return -1;
        long end = System.currentTimeMillis();
        return end - start ;
    }

    @Override
    public String getName(){
        return name;
    }

    @Override
    public String getHost() {
        return host;
    }

    @Override
    public int getPort() {
        return port;
    }
}
