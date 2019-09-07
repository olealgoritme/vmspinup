package com.lemon.vmspinup.model.hypervisor;

import java.net.URI;
import java.net.URISyntaxException;

public class LXC extends HyperVisor {

    private static LXC instance;

    private LXC() {
        super();
        this.type = "lxc";
        this.name = "lxc";
        try {
            this.uri = new URI("lxc:///");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static LXC getInstance() {
        if (instance != null)
            instance = new LXC();
        return instance;
    }
}
