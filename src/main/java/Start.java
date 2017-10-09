import backend.general.Factory;

/**
 * Created by Felix on 08.10.2017.
 */
public class Start {
    public static void main(String[] args) {
        System.out.println(Factory.getConfig().getMaxVLCConnectionThreads());
    }
}
