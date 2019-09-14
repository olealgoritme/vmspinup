package com.lemon.vmspinup.xml.vm;

import com.lemon.vmspinup.xml.storage.Disk;
import org.eclipse.persistence.oxm.annotations.XmlPath;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "domain")
@XmlType (name = "", propOrder = {"uuid", "name", "memory", "vcpu", "os", "features", "devices" })
public class Domain {

    @XmlAttribute(name = "type", required = true)
    private String type;

    private String uuid;

    @XmlElement(required =  true)
    private String name;

    @XmlElement(required = true)
    private long memory;

    @XmlPath("memory/@unit") // e.g 'bytes'
    private String memoryUnit;

    @XmlElement(required = true)
    private int vcpu;

    @XmlElement(required = true)
    private OS os;

    private Features features;

    @XmlElement(required = true)
    private Devices devices;
    

    public Domain() {
       os = new OS();
       devices = new Devices();
       features = new Features();
    }

    public Domain setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getUuid() {
        return this.uuid;
    }

    public String getName() {
        return this.name;
    }

    public Domain setName(String name) {
        this.name = name;
        return this;
    }

    public int getVcpu() {
        return this.vcpu;
    }

    public Domain setVcpu(int vcpus) {
        this.vcpu = vcpus;
        return this;
    }

    public long getMemory() {
        return this.memory;
    }

    public Domain setMemory(long memory) {
        this.memory = memory;
        return this;
    }

    public OS getOs() {
        return this.os;
    }

    public Domain setOs(OS os) {
        this.os = os;
        return this;
    }

    public Devices getDevices() {
        return this.devices;
    }

    public Domain setDevices(Devices devices) {
        this.devices = devices;
        return this;
    }

    public Features getFeatures() {
        return this.features;
    }

    public Domain setFeatures(Features features) {
        this.features = features;
        return this;
    }

    public Domain addDisk(Disk disk) {
        this.getDevices().addDisk(disk);
        return this;
    }
  
    public Domain addInterface(Interface interf) {
        this.getDevices().addInterface(interf);
        return this;
    }
    
    public Domain addSerial(SerialConsole serial) {
        this.getDevices().addSerial(serial);
        return this;
    }
    
    public Domain addConsole(SerialConsole console) {
        this.getDevices().addSerial(console);
        return this;
    }

    public Domain addGraphics(Graphics graphics) {
        this.getDevices().addGraphics(graphics);
        return this;
    }

    public String getType() {
        return this.type;
    }

    public Domain setType(String type) {
        this.type = type;
        return this;
    }

    public String getMemoryUnit() {
        return memoryUnit;
    }

    public Domain setMemoryUnit(String memoryUnit) {
        this.memoryUnit = memoryUnit;
        return this;
    }


}
