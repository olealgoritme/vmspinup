package com.lemon.vmspinup.xml.volume;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlRootElement(name = "volume")
@XmlAccessorType(XmlAccessType.FIELD)

public class LibvirtConfigVolume {
    
    @XmlElement(name = "key")
    private String key;
    
    @XmlElement(name = "name")
    private String name;
    
    @XmlElement(name = "allocation")
    private long allocation;
    
    @XmlPath("allocation/@unit")
    private String allocationUnit;
    
    @XmlElement(name = "capacity")
    private long capacity;
    
    @XmlPath("capacity/@unit")
    private String capacityUnit;
    
    @XmlElement(name = "backingStore")
    private LibvirtConfigVolumeInfo backingStore;
    
    @XmlElement(name = "target")
    private LibvirtConfigVolumeInfo target;
    
    @XmlElement(name = "source")
    private LibvirtConfigVolumeSource source;
    
    public LibvirtConfigVolume() {
    }

    public String getName() {
        return name;
    }

    public LibvirtConfigVolume setKey(String key) {
        this.key = key;
        return this;
    }

    public LibvirtConfigVolume setAllocation(long allocation) {
        this.allocation = allocation;
        return this;
    }

    public String getAllocationUnit() {
        return allocationUnit;
    }

    public LibvirtConfigVolume setAllocationUnit(String allocationUnit) {
        this.allocationUnit = allocationUnit;
        return this;
    }

    public long getCapacity() {
        return capacity;
    }

    public LibvirtConfigVolume setCapacity(long capacity) {
        this.capacity = capacity;
        return this;
    }

    public String getCapacityUnit() {
        return capacityUnit;
    }

    public LibvirtConfigVolume setCapacityUnit(String capacityUnit) {
        this.capacityUnit = capacityUnit;
        return this;
    }

    public LibvirtConfigVolumeInfo getBackingStore() {
        return backingStore;
    }

    public LibvirtConfigVolume setBackingStore(LibvirtConfigVolumeInfo backingStore) {
        this.backingStore = backingStore;
        return this;
    }

    public LibvirtConfigVolumeInfo getTarget() {
        return target;
    }

    public LibvirtConfigVolume setTarget(LibvirtConfigVolumeInfo target) {
        this.target = target;
        return this;
    }

    public LibvirtConfigVolumeSource getSource() {
        return source;
    }

    public LibvirtConfigVolume setSource(LibvirtConfigVolumeSource source) {
        this.source = source;
        return this;
    }

  
    
}
