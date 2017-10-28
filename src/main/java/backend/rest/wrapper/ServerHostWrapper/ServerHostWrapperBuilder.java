package backend.rest.wrapper.ServerHostWrapper;

/**
 * Created by Felix on 26.10.2017.
 */
public class ServerHostWrapperBuilder {
    private ServerHostWrapper[] wrapper;

    public ServerHostWrapperBuilder(String[] rawHosts, String serverPath){
        wrapper = new ServerHostWrapper[rawHosts.length];
        for (int i=0; i<wrapper.length; i++){
            wrapper[i] = new ServerHostWrapper(rawHosts[i], serverPath + i);
        }
    }

    public ServerHostWrapper[] create(){
        return wrapper;
    }

}
