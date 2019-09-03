package model.hypervisors;

public class KVM extends AbstractHyperVisor {

    private static KVM instance;

    private KVM() {
        super();
        this.name = "KVM";
        this.type = "kvm";
    }

    public static KVM getInstance() {
        if(instance == null)
            instance = new KVM();
        return instance;
    }

}
