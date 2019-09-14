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

public class Arch {
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
    private ArrayList<Machine> machines;
    
    @XmlElement(name = "loader")
    private String loader;
    public Arch() {
        machines = new ArrayList<Machine>();
    }
    public String getName() {
        return name;
    }

    public Arch setName(String name) {
        this.name = name;
        return this;
    }
    public String getWordsize() {
        return wordsize;
    }

    public Arch setWordsize(String wordsize) {
        this.wordsize = wordsize;
        return this;
    }

    public String getDomain() {
        return domain;
    }

    public Arch setDomain(String domain) {
        this.domain = domain;
        return this;
    }

    public String getType() {
        return type;
    }

    public Arch setType(String type) {
        this.type = type;
        return this;
    }
    public String getEmulator() {
        return emulator;
    }

    public Arch setEmulator(String emulator) {
        this.emulator = emulator;
        return this;
    }

    public ArrayList<Machine> getMachines() {
        return machines;
    }

    public String getLoader() {
        return loader;
    }

    public Arch setLoader(String loader) {
        this.loader = loader;
        return this;
    }

  
    
}
