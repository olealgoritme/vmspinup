package model.hypervisors;

public class LXC extends AbstractHyperVisor {

    private static LXC instance;

    private LXC() {
        super();
        this.type = "LXC";
        this.name = "lxc";
    }

    public LXC getInstance() {
        if (instance != null)
            instance = new LXC();
        return instance;
    }
}
