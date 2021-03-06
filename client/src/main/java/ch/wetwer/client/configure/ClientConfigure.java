package ch.wetwer.client.configure;

import ch.wetwer.client.handler.ResponseSender;
import ch.wetwer.client.service.WebHandler;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Date;

/**
 * @author Wetwer
 * @project server-control
 **/

public class ClientConfigure {

    public void setPcName() {
        try {
            ClientValues.pcName = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void setServer(String server) {
        setPcName();
        ClientValues.server = server;
        ClientValues.commandReader = new WebHandler(server + "/out/" + ClientValues.pcName);
        new ResponseSender("init", "Client initialized at " + new Date());
    }

    public void setIp() {
        try {
            new ResponseSender("ip", InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    public void setPcUser() {
        new ResponseSender("user", System.getProperty("user.name"));
    }

    public void setArch(String arch) {
        new ResponseSender("arch", arch);
        this.setIp();
        this.setPcUser();
        this.setOs();
    }

    public void setOs() {
        new ResponseSender("os", System.getProperty("os.name"));
    }

}
