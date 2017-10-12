package backend.general.vlcHolder.impl;

import backend.general.Factory;
import backend.general.vlc.VLCCommand;
import backend.general.vlc.VLC;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.tomcat.jni.Time;

import java.util.LinkedList;
import java.util.concurrent.*;
import java.util.logging.Level;

/**
 * Created by Felix on 09.10.2017.
 */
public class VLCHolder extends backend.general.vlcHolder.VLCHolder {

    private VLC[] vlcs;

    protected VLCHolder(VLC[] vlcs){
        this.vlcs = vlcs;
    }

    public void connect(){
        for (VLC vlc : vlcs){
            if (!vlc.connect())
                Factory.getLogger().log(Level.WARNING, "Could not connect to " + vlc.getName());
        }
    }

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
