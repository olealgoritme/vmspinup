package com.lemon.vmspinup.xml.vm;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * Features.
 * 
 * @author msimonin
 *
 */
@XmlRootElement(name = "features")
public class Features
{
    /**acpi.*/
    @XmlElement(name = "acpi")
    private String acpi;
    
    /** apic. */
    @XmlElement(name = "apic")
    private String apic;

    /** pae.*/
    @XmlElement(name = "pae")
    private String pae;
    
    
    /**
     * 
     * Enables acpi.
     * 
     * @return LibvirtConfigFeatures
     */
    public Features enableAcpi()
    {
        acpi = "";
        return this;
    }
    
    /**
     * 
     * Disabe Acpi.
     * 
     * @return LibvirtConfigFeatures
     */
    public Features disableAcpi()
    {
        acpi = null;
        return this;
    }
    

    /**
     * 
     * Enables apic.
     * 
     * @return LibvirtConfigFeatures
     */
    public Features enableApic()
    {
        apic = "";
        return this;
    }
    
    /**
     * 
     * Disabe Apic.
     * 
     * @return LibvirtConfigFeatures
     */
    public Features disableApic()
    {
        apic = null;
        return this;
    }

    /**
     * 
     * Enables apic.
     * 
     * @return LibvirtConfigFeatures
     */
    public Features enablePae()
    {
        pae = "";
        return this;
    }
    
    /**
     * 
     * Disabe pae.
     * 
     * @return LibvirtConfigFeatures
     */
    public Features disablePae()
    {
        pae = null;
        return this;
    }
    
    
}
