package com.lemon.vmspinup.xml.domain;
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
public class LibvirtConfigFeatures
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
    public LibvirtConfigFeatures enableAcpi()
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
    public LibvirtConfigFeatures disableAcpi()
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
    public LibvirtConfigFeatures enableApic()
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
    public LibvirtConfigFeatures disableApic()
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
    public LibvirtConfigFeatures enablePae()
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
    public LibvirtConfigFeatures disablePae()
    {
        pae = null;
        return this;
    }
    
    
}
