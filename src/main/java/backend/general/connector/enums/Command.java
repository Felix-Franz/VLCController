package backend.general.connector.enums;

/**
 * Created by Felix on 09.10.2017.
 */

//Command.PLAY.getCommand() --> play

public enum Command {
    BACKWARD("prev"),
    PLAY("play"),
    PAUSE("pause"),
    STOP("stop"),
    FORWARD("next"),
    FULLSCREEN("fullscreen"),
    SHUFFLE("random"),
    REPEAT("loop"),
    RESET("seek 0");

    private String value;

    Command(String value){
        this.value = value;
    }

    public String getCommand() {
        return value;
    }
}
