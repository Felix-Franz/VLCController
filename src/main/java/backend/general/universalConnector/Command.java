package backend.general.universalConnector;

/**
 * Created by Felix on 09.10.2017.
 */

//Command.PLAY.getCommand() --> play

public enum Command {
    PLAY("play"),
    PAUSE("pause"),
    STOP("stop"),
    BACKWARD("prev"),
    FULLSCREEN("fullscreen"),
    SHUFFLE("random"),
    REPEAT("loop"),
    FORWARD("next");

    private String value;

    Command(String value){
        this.value = value;
    }

    public String getCommand() {
        return value;
    }
}
