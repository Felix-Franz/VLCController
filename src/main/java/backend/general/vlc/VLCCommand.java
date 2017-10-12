package backend.general.vlc;

/**
 * Created by Felix on 09.10.2017.
 */

//VLCCommand.PLAY.getCommand() --> play

public enum VLCCommand {
    PLAY("play"),
    PAUSE("pause"),
    STOP("stop"),
    BACKWARD("prev"),
    SHUFFLE("random"),
    REPEAT("loop"),
    FORWARD("next");

    private String value;

    VLCCommand(String value){
        this.value = value;
    }

    public String getCommand() {
        return value;
    }
}
