package backend.general.connector.enums;

/**
 * Created by Felix on 21.10.2017.
 */
public enum PlayerState {
    PLAYING("playing"),
    PAUSED("paused"),
    STOPPED("stopped"),
    UNDEFINED("undefined");

    private String textState;

    private PlayerState(String textState){
        this.textState = textState;
    }

    @Override
    public String toString() {
        return textState;
    }
}
