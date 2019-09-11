package com.lemon.vmspinup.xml.domain;


import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * 
 * Libvirt domain class.
 * 
 * @author msimonin
 *
 */
/**
 * @author msimonin
 *
 */
@XmlRootElement(name = "domain")
@XmlType (name = "", propOrder = {"uuid", "name", "memory", "vcpu", "os", "features", "devices" })
public class LibvirtConfigDomain 
{
    /** virtualization type. */
    private String type;
    
    /** domain uuid.*/
    private String uuid;
    
    /** domain name.*/
    private String name;
    
    /** domain vcpus.*/
    private int vcpu;
    
    /** domain memory.*/
    private long memory;

    /** domain os.*/
    private LibvirtConfigOs os;

    /** devices.*/
    private LibvirtConfigDevices devices;
    
    /** features.*/
    private LibvirtConfigFeatures features;
    

    /**
     * 
     */
    public LibvirtConfigDomain()
    {
       os = new LibvirtConfigOs();
       devices = new LibvirtConfigDevices();
       features = new LibvirtConfigFeatures();
    }

    /**
     * @return the type
     */
    @XmlAttribute(name = "type")
    public String getType() 
    {
        return type;
    }

    /**
     * @param type the type to set
     * @return this
     */
    public LibvirtConfigDomain setType(String type)
    {
        this.type = type;
        return this;
    }

    /**
     * @return the uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * @param uuid the uuid to set
     * @return this
     */
    public LibvirtConfigDomain setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    /**
     * @return the name
     * 
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     * @return this
     */
    public LibvirtConfigDomain setName(String name) {
        this.name = name;
        return this;
    }

    /**
     * @return the vcpus
     */
    public int getVcpu() {
        return vcpu;
    }

    /**
     * @param vcpus the vcpus to set
     * @return this
     */
    public LibvirtConfigDomain setVcpu(int vcpus) {
        this.vcpu = vcpus;
        return this;
    }

    /**
     * @return the memory
     */
    public long getMemory() {
        return memory;
    }

    /**
     * @param memory the memory to set
     * @return this
     */
    public LibvirtConfigDomain setMemory(long memory) {
        this.memory = memory;
        return this;
    }

    /**
     * @return the os
     */
    public LibvirtConfigOs getOs() {
        return os;
    }

    /**
     * @param os the os to set
     * @return this
     */
    public LibvirtConfigDomain setOs(LibvirtConfigOs os) {
        os = os;
        return this;
    }

    /**
     * @return the devices
     */
    public LibvirtConfigDevices getDevices() {
        return devices;
    }

    /**
     * @param devices the devices to set
     * @return this
     */
    public LibvirtConfigDomain setDevices(LibvirtConfigDevices devices) {
        devices = devices;
        return this;
    }

    /**
     * @return the features
     */
    public LibvirtConfigFeatures getFeatures()
    {
        return features;
    }

    /**
     * @param features the features to set
     * @return this
     */
    public LibvirtConfigDomain setFeatures(LibvirtConfigFeatures features)
    {
        features = features;
        return this;
    }

    /**
     * 
     * Add a disk.
     * 
     * @param disk      The disk to add.
     * @return 
     * @return this
     */
    public LibvirtConfigDomain addDisk(LibvirtConfigDisk disk)
    {
        getDevices().addDisk(disk);
        return this;
    }
  
    /**
     * 
     * Add an interface.
     * 
     * @param interf     The interface to add.
     * @return this
     */
    public LibvirtConfigDomain addInterface(LibvirtConfigInterface interf)
    {
        getDevices().addInterface(interf);
        return this;
    }
    
    /**
     * 
     * Add an interface.
     * 
     * @param serial     The serial to add.
     * @return this
     */
    public LibvirtConfigDomain addSerial(LibvirtConfigSerialConsole serial)
    {
        getDevices().addSerial(serial);
        return this;
    }
    
    /**
     * 
     * Add an interface.
     * 
     * @param console   The console to add.
     * @return this
     */
    public LibvirtConfigDomain addConsole(LibvirtConfigSerialConsole console)
    {
        getDevices().addSerial(console);
        return this;
    }

    /**
     * 
     * Add a graphic.
     * 
     * @param graphics   The graphics to add.
     * @return this
     */
    public LibvirtConfigDomain addGraphics(LibvirtConfigGraphics graphics)
    {
        getDevices().addGraphics(graphics);
        return this;
    }
    /**
     * 
     * Sets the os type.
     * 
     * @param type  the type
     */
    public void setOsType(String type)
    {
        getOs().setType(type);
    }
}
