package backend.utils;

import backend.general.Factory;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;

/**
 * Created by Felix on 20.10.2017.
 */
public class IPFinder {
    String[] ips;

    public IPFinder(){
        try {
            InetAddress[] localAddress = InetAddress.getAllByName(InetAddress.getLocalHost().getCanonicalHostName());
            ips = new String[localAddress.length];
            for (int i=0; i<localAddress.length; i++){
                ips[i] = localAddress[i].getHostAddress();
            }
            formatIPv6();
        } catch (UnknownHostException e) {
            Factory.getLogger().log(Level.WARNING, "Could not determine IP- & Host-Addresses!");
        }
    }

    private void formatIPv6(){
        for (int i=0; i<ips.length; i++){
            if (ips[i].matches("^.+:.+:.+:.+:.+:.+:.+:.+")){    //checks if ip is IPv6
                ips[i] = '[' + ips[i].substring(0, ips[i].indexOf('%')) + ']';
            }
        }
    }

    public IPFinder addPort(int port){
        for (int i=0; i<ips.length; i++)
            ips[i] += ":" + port;
        return this;
    }

    public IPFinder addSettingsPort(){
        addPort(Factory.getSettings().getPort());
        return this;
    }

    public String[] getIps(){
        String[] out = new String[ips.length];
        for (int i=0; i<ips.length; i++)
            out[i] = "http://" + ips[i];
        return out;
    }

    @Override
    public String toString() {
        if (ips.length==0) return "";
        String out = "http://" + ips[0];
        for (int i=1; i<ips.length; i++)
            out+= ", http://" + ips[i];
        return out;
    }
}
