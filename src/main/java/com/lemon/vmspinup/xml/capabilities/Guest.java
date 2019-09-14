package com.lemon.vmspinup.xml.capabilities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.lemon.vmspinup.xml.vm.Features;

/**
 * @author msimonin
 *
 */
@XmlRootElement(name = "guest")
@XmlAccessorType(XmlAccessType.FIELD)
public class Guest {
    /** ostype.*/
    @XmlElement(name = "ostype")
    private String osType;
    
    /** arch. */
    @XmlElement(name = "arch")
    private Arch arch;
    
    /** features.*/
    @XmlElement(name = "features")
    private Features features;

    
    /**
     * 
     */
    public Guest() {
        arch = new Arch();
        features = new Features();
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
    public Guest setOsType(String osType) {
        osType = osType;
        return this;
    }

    /**
     * @return the arch
     */
    public Arch getArch() {
        return arch;
    }

    /**
     * @param arch the arch to set
     * @return this
     */
    public Guest setArch(Arch arch) {
        arch = arch;
        return this;
    }

    /**
     * @return the features
     */
    public Features getFeatures() {
        return features;
    }

    /**
     * @param features the features to set
     * @return this
     */
    public Guest setFeatures(Features features) {
        features = features;
        return this;
    }
    
    
}
