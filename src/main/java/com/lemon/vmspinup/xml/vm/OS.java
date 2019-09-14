package com.lemon.vmspinup.xml.vm;

import javax.xml.bind.annotation.*;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlRootElement(name = "os")

@XmlType(propOrder = {"type", "boot" })
@XmlAccessorType(XmlAccessType.FIELD)
public class OS {

    @XmlElement(name = "type", required =  true)
    private String type;
    
    @XmlPath("type/@arch")
    private String typeArch;
    
    @XmlPath("type/@machine")
    private String typeMachine;

    @XmlElement(name = "boot")
    private String boot;

    @XmlPath("boot/@dev")
    private String bootDev;

    public String getType() {
        return this.type;
    }

    public OS setType(String type) {
        this.type = type;
        return this;
    }

    public String getTypeArch() {
        return this.typeArch;
    }

    public OS setTypeArch(String typeArch) {
        this.typeArch = typeArch;
        return this;
    }

    public String getTypeMachine() {
        return this.typeMachine;
    }


    public OS setTypeMachine(String typeMachine) {
        this.typeMachine = typeMachine;
        return this;
    }

    public String getBootDev() {
        return this.bootDev;
    }

    public OS setBootDev(String bootDev) {
        this.bootDev = bootDev;
        return this;
    }

    
    
    
    
    
}
