package com.lemon.vmspinup.xml.capabilities;

import org.eclipse.persistence.oxm.annotations.XmlPath;
import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "arch")
@XmlAccessorType(XmlAccessType.FIELD)

public class LibvirtConfigArch  {
    @XmlAttribute(name = "name")
    private String name;
    
    @XmlElement(name = "wordsize")
    private String wordsize;
    
    @XmlElement(name = "domain")
    private String domain;
    
    @XmlPath("domain/@type")
    private String type;
    
    @XmlElement(name = "emulator")
    private String emulator;
    
    @XmlElement(name = "machine")
    private ArrayList<LibvirtConfigMachine> machines;
    
    @XmlElement(name = "loader")
    private String loader;
    public LibvirtConfigArch() {
        machines = new ArrayList<LibvirtConfigMachine>();
    }
    public String getName() {
        return name;
    }

    public LibvirtConfigArch setName(String name) {
        this.name = name;
        return this;
    }
    public String getWordsize() {
        return wordsize;
    }

    public LibvirtConfigArch setWordsize(String wordsize) {
        this.wordsize = wordsize;
        return this;
    }

    public String getDomain() {
        return domain;
    }

    public LibvirtConfigArch setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public String getType() {
        return type;
    }

    public LibvirtConfigArch setType(String type) {
        this.type = type;
        return this;
    }
    public String getEmulator() {
        return emulator;
    }

    public LibvirtConfigArch setEmulator(String emulator) {
        this.emulator = emulator;
        return this;
    }

    public ArrayList<LibvirtConfigMachine> getMachines() {
        return machines;
    }

    public String getLoader() {
        return loader;
    }

    public LibvirtConfigArch setLoader(String loader) {
        this.loader = loader;
        return this;
    }

  
    
}
