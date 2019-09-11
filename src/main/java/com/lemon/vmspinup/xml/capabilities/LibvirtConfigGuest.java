package com.lemon.vmspinup.xml.capabilities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.lemon.vmspinup.xml.domain.LibvirtConfigFeatures;

/**
 * @author msimonin
 *
 */
@XmlRootElement(name = "guest")
@XmlAccessorType(XmlAccessType.FIELD)
public class LibvirtConfigGuest {
    /** ostype.*/
    @XmlElement(name = "ostype")
    private String osType;
    
    /** arch. */
    @XmlElement(name = "arch")
    private LibvirtConfigArch arch;
    
    /** features.*/
    @XmlElement(name = "features")
    private LibvirtConfigFeatures features;

    
    /**
     * 
     */
    public LibvirtConfigGuest() {
        arch = new LibvirtConfigArch();
        features = new LibvirtConfigFeatures();
    }

    /**
     * @return the osType
     */
    public String getOsType() {
        return osType;
    }

    /**
     * @param osType the osType to set
     * @return this
     */
    public LibvirtConfigGuest setOsType(String osType) {
        osType = osType;
        return this;
    }

    /**
     * @return the arch
     */
    public LibvirtConfigArch getArch() {
        return arch;
    }

    /**
     * @param arch the arch to set
     * @return this
     */
    public LibvirtConfigGuest setArch(LibvirtConfigArch arch) {
        arch = arch;
        return this;
    }

    /**
     * @return the features
     */
    public LibvirtConfigFeatures getFeatures() {
        return features;
    }

    /**
     * @param features the features to set
     * @return this
     */
    public LibvirtConfigGuest setFeatures(LibvirtConfigFeatures features) {
        features = features;
        return this;
    }
    
    
}
