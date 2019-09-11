package com.lemon.vmspinup.xml.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "graphics")

public class LibvirtConfigGraphics {
    @XmlAttribute(name = "type")
    private String type;
    
    @XmlAttribute(name = "listen")
    private String listen;
    
    @XmlAttribute(name = "port")
    private String port;

    @XmlAttribute(name = "keymap")
    private String keymap;

    public String getType() {
        return type;
    }

    public LibvirtConfigGraphics setType(String type) {
        this.type = type;
        return this;
    }

    public String getListen() {
        return listen;
    }

    public LibvirtConfigGraphics setListen(String listen) {
        this.listen = listen;
        return this;
    }

    public String getPort() {
        return port;
    }

    public LibvirtConfigGraphics setPort(String port) {
        this.port = port;
        return this;
    }

    public LibvirtConfigGraphics setKeymap(String keymap) {
        this.keymap = keymap;
        return this;
        
    }

    public String getKeymap() {
        return keymap;
    }
    
   
}
