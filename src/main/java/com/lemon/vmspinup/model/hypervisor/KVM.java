package com.lemon.vmspinup.model.hypervisor;

import java.net.URI;
import java.net.URISyntaxException;

public class KVM extends HyperVisor {

    private static KVM instance;

    public KVM() {
        super();
        this.name = "KVM";
        this.type = "kvm";
        try {
            this.uri = new URI("qemu:///system");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public static KVM getInstance() {
        if(instance == null)
            instance = new KVM();
        return instance;
    }

}
