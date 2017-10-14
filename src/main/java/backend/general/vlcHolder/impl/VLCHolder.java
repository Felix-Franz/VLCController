package backend.general.vlcHolder.impl;

import backend.CONFIG;
import backend.general.Factory;
import backend.general.settings.impl.Settings;
import backend.general.vlc.VLCCommand;
import backend.general.vlc.VLC;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.tomcat.jni.Time;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.*;
import java.util.logging.Level;

/**
 * Created by Felix on 09.10.2017.
 */
public class VLCHolder extends backend.general.vlcHolder.VLCHolder {

    private VLC[] vlcs;

    /**
     * creates a vlc holder
     *
     * @param vlcs vlc instances
     */
    protected VLCHolder(VLC[] vlcs){
        this.vlcs = vlcs;
    }

    /**
     * creates a vlc hodler
     * @param vlcs vlc instances
     * @param saveVLCs true: save to config file
     */
    protected VLCHolder(VLC[] vlcs, boolean saveVLCs){
        this.vlcs = vlcs;
        saveVLCs();
    }

    /**
     * save all vlcs to config file
     */
    private void saveVLCs(){
        try {
            FileWriter writer = new FileWriter(CONFIG.VLC_CONFIG_PATH);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(vlcs, VLC[].class, writer);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();        //ToDo handle exception
        }
    }

    /**
     * establishes all connections to vlc
     */
    public void connect(){
        for (VLC vlc : vlcs){
            if (!vlc.connect())
                Factory.getLogger().log(Level.WARNING, "Could not connect to " + vlc.getName());
        }
    }

    /**
     * send a command to all vlc instances
     *
     * @param command command type
     */
    public void runCommand(VLCCommand command){
        Factory.getLogger().log(Level.INFO,"used command " + command + " on all vlc instances!");

        ExecutorService executorService = Executors.newFixedThreadPool(Factory.getSettings().getMaxVLCConnectionThreads());
        long executeTime = System.currentTimeMillis() + 100;

        for (VLC vlc : vlcs){

            Runnable runCommand = new Runnable() {
                @Override
                public void run() {
                    while(System.currentTimeMillis() < executeTime) {
                        try {
                            Thread.currentThread().sleep((executeTime - System.currentTimeMillis()) / 2);
                        } catch (InterruptedException e) {
                            e.printStackTrace();        //ToDo handle exception
                        }
                    }
                    if (!vlc.runCommand(command))
                        Factory.getLogger().log(Level.WARNING, "Failed to run " + command.getCommand() + " on " + vlc.getName());
                }
            };
            executorService.submit(runCommand);
        }
        executorService.shutdown();
    }
}
