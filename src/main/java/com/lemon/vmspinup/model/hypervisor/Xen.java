package com.lemon.vmspinup.model.hypervisor;

public class Xen extends HyperVisor {

    private static Xen instance;

    private Xen() {
        super();
        this.name = "XEN";
        this.type = "xen";
    }

    public static Xen getInstance() {
        if(instance == null)
            instance = new Xen();
        return instance;
    }
}
