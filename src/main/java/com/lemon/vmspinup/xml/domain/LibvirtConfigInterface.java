package com.lemon.vmspinup.xml.domain;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "interface")

public class LibvirtConfigInterface {
    /** Device.*/
    @XmlAttribute(name = "type")
    private String type;

    /** mac.*/
    @XmlElement(name = "mac")
    private String mac;
    
    /**mac address.*/
    @XmlPath("mac/@address")
    private String macAddress;
    
    /** source.*/
    @XmlElement(name = "source")
    private String bridge;
    
    /** source bridge.*/
    @XmlPath("source/@bridge")
    private String sourceBridge;
    
    /** target. */
    @XmlElement(name = "target")
    private String target;
    
    /** target dev. */
    @XmlPath("target/@dev")
    private String targetDev;
    
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     * @return this
     */
    public LibvirtConfigInterface setType(String type) {
        this.type = type;
        return this;
    }

    /**
     * @return the macAddress
     */
    public String getMacAddress() {
        return macAddress;
    }

    /**
     * @param macAddress the macAddress to set
     * @return this
     */
    public LibvirtConfigInterface setMacAddress(String macAddress) {
        this.macAddress = macAddress;
        return this;
    }
    

    /**
     * @return the sourceBridge
     */
    public String getSourceBridge() {
        return sourceBridge;
    }

    /**
     * @param sourceBridge the sourceBridge to set
     * @return this.
     */
    public LibvirtConfigInterface setSourceBridge(String sourceBridge) {
        this.sourceBridge = sourceBridge;
        return this;
    }

    /**
     * @return the targetDev
     */
    public String getTargetDev() {
        return targetDev;
    }

    /**
     * @param targetDev the targetDev to set
     */
    public void setTargetDev(String targetDev) {
        this.targetDev = targetDev;
    }
}
