import backend.general.Factory;
import backend.general.vlcHolder.impl.VLCHolder;

/**
 * Created by Felix on 08.10.2017.
 */
public class Start {
    public static void main(String[] args) {
        System.out.println(Factory.getSettings().getPort());
    }
}
