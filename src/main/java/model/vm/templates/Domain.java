package model.vm.templates;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias(value="domain")

public class Domain {



    private String name;
    private String uuid;
    private String metadata;

    private String memory;
    @XStreamAsAttribute
    private String unit;                    // <memory unit='KiB'></memory>
    private String currentMemory;           // <currentMemory unit='KiB'></currentMemory>

    private String vcpu;
    @XStreamAsAttribute
    private String placement;               // <vcpu placement='static'></vcpu>

    private String os;
    @XStreamAsAttribute
    private String type;                    // <os><domain type='kvm'></os>



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }
}