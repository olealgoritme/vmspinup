package com.lemon.vmspinup.xml.domain;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlAccessorType(XmlAccessType.FIELD)

public class LibvirtConfigSerialConsole  {
    @XmlAttribute(name = "type")
    private String type;
    
    @XmlElement(name = "source")
    private String source;
    
    @XmlPath("source/@path")
    private String sourcePath;
    
    @XmlElement(name = "target")
    private String target;
    
    @XmlPath("target/@type")
    private String targetType;
    
    @XmlPath("target/@port")
    private String targetPort;

    public String getSourcePath() {
        return sourcePath;
    }

    public LibvirtConfigSerialConsole setSourcePath(String sourcePath) {
        this.sourcePath = sourcePath;
        return this;
    }

    public String getTargetType() {
        return targetType;
    }

    public LibvirtConfigSerialConsole setTargetType(String targetType) {
        this.targetType = targetType;
        return this;
    }

    /**
     * @return the targetPort
     */
    public String getTargetPort() {
        return targetPort;
    }

    /**
     * @param targetPort the targetPort to set
     * @return this
     */
    public LibvirtConfigSerialConsole setTargetPort(String targetPort) {
        targetPort = targetPort;
        return this;
    }

    public String getType() {
        return type;
    }

    public LibvirtConfigSerialConsole setType(String type) {
        this.type = type;
        return this;
    }
}
