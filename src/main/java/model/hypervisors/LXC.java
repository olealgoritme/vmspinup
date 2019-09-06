package model.hypervisors;

import java.net.URI;
import java.net.URISyntaxException;

public class LXC extends HyperVisor {

    private static LXC instance;

    private LXC() {
        super();
        this.type = "LXC";
        this.name = "lxc";
        try {
            this.uri = new URI("lxc:///");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public LXC getInstance() {
        if (instance != null)
            instance = new LXC();
        return instance;
    }
}
