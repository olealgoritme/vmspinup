package com.lemon.vmspinup.xml.vm;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "features")
public class Features  {
    @XmlElement(name = "acpi")
    private String acpi;
    
    @XmlElement(name = "apic")
    private String apic;

    @XmlElement(name = "pae")
    private String pae;

    public enum TYPE {
        ACPI,
        APIC,
        PAE
    }
    /**
     *
     * Enables apic.
     *
     * @return LibvirtConfigFeatures
     */
    public Features enableAcpi() {
        acpi = "";
        return this;
    }
    
    public Features disableAcpi() {
        acpi = null;
        return this;
    }
    
    public Features enableApic()
    {
        apic = "";
        return this;
    }
    
    public Features disableApic()
    {
        apic = null;
        return this;
    }

    public Features enablePae()
    {
        pae = "";
        return this;
    }
    
    public Features disablePae()
    {
        pae = null;
        return this;
    }
    
    
}
