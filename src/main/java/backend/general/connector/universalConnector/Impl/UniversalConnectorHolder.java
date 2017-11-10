package backend.general.connector.universalConnector.Impl;

import backend.CONFIG;
import backend.general.Factory;
import backend.general.connector.enums.Command;
import backend.general.connector.enums.PlayerState;
import backend.general.connector.universalConnector.UniversalConnector;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.*;
import java.util.logging.Level;

/**
 * Created by Felix on 09.10.2017.
 */
public class UniversalConnectorHolder extends backend.general.connector.universalConnector.UniversalConnectorHolder {

    private backend.general.connector.universalConnector.UniversalConnector[] universalConnectors;

    private PlayerState state = PlayerState.STOPPED;
    private int volume = 100;

    /**
     * creates a universalConnector holder
     *
     * @param universalConnectors universalConnector instances
     */
    protected UniversalConnectorHolder(UniversalConnector[] universalConnectors){
        this.universalConnectors = universalConnectors;
    }

    /**
     * creates a universalConnector holder
     * @param universalConnectors universalConnector instances
     * @param saveVLCs true: save to config file
     */
    protected UniversalConnectorHolder(UniversalConnector[] universalConnectors, boolean saveVLCs){
        this.universalConnectors = universalConnectors;
        saveVLCs();
    }

    /**
     * save all universalConnectors to config file
     */
    protected void saveVLCs(){
        try {
            Factory.getLogger().log(Level.INFO, "writing player configuration to file!");
            FileWriter writer = new FileWriter(CONFIG.VLC_CONFIG_PATH);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(universalConnectors, UniversalConnector[].class, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            Factory.getLogger().log(Level.WARNING, "Could not save player configuration file!");
        }
    }

    @Override
    public void connect(){
        Factory.getLogger().log(Level.INFO, "Connecting to all player Instances...");
        for (UniversalConnector universalConnector : universalConnectors){
            universalConnector.connect();
        }
    }

    @Override
    public void disconnect(){
        Factory.getLogger().log(Level.INFO, "Disconnecting from all player Instances...");
        for (UniversalConnector universalConnector : universalConnectors) {
            universalConnector.disconnect();
        }
    }

    public void runCommand(Command command){
        Factory.getLogger().log(Level.INFO,"run command " + command + " on all universalConnector instances!");

        //update master config (for outputs)
        switch (command){
            case PLAY:
                this.state = PlayerState.PLAYING;
                break;
            case PAUSE:
                this.state = PlayerState.PAUSED;
                break;
            case STOP:
                this.state = PlayerState.STOPPED;
                break;
        }

        //single instance
        if (Factory.getSettings().getMaxVLCConnectionThreads() == 0){
            for (UniversalConnector universalConnector : universalConnectors)
                universalConnector.runCommand(command);
            return;
        }

        ExecutorService executorService = Executors.newFixedThreadPool(Factory.getSettings().getMaxVLCConnectionThreads());
        long executeTime = System.currentTimeMillis() + 100;

        for (UniversalConnector universalConnector : universalConnectors){

            Runnable runCommand = new Runnable() {
                @Override
                public void run() {
                    while(System.currentTimeMillis() < executeTime) {
                        try {
                            Thread.currentThread().sleep((executeTime - System.currentTimeMillis()) / 2);
                        } catch (InterruptedException e) {
                            Factory.getLogger().log(Level.WARNING, "Could not run command " + command + "!");
                        }
                    }
                    if (!universalConnector.runCommand(command))
                        Factory.getLogger().log(Level.WARNING, "Failed to run " + command.getCommand() + " on " + universalConnector.getName());
                }
            };
            executorService.submit(runCommand);
        }
        executorService.shutdown();
    }

    @Override
    public UniversalConnector getUniversalConnectorInstance(String name) {
        for (UniversalConnector instance : universalConnectors)
            if (instance.getName().equals(name))
                return instance;
        return null;
    }

    @Override
    public UniversalConnector[] getUniversalConnectorInstances(){
        return universalConnectors;
    }

    @Override
    public PlayerState getState() {
        return state;
    }

    @Override
    public void setVolume(int volume) {
        this.volume = volume;
        for (UniversalConnector connector : universalConnectors)
            connector.setVolume(volume);
    }

    @Override
    public int getVolume() {
        return this.volume;
    }
}
