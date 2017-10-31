package backend.general.connector.universalConnector.universalConnectorHolderImpl;

import backend.CONFIG;
import backend.general.Factory;
import backend.general.connector.enums.Command;
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

    private UniversalConnector[] universalConnectors;

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

    /**
     * send a command to all universalConnector instances
     *
     * @param command command type
     */
    public void runCommand(Command command){
        Factory.getLogger().log(Level.INFO,"run command " + command + " on all universalConnector instances!");

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

    public UniversalConnector[] getUniversalConnectorInstances(){
        return universalConnectors;
    }

    @Override
    public void setVolume(int volume) {
        for (UniversalConnector connector : universalConnectors)
            connector.setVolume(volume);
    }
}
