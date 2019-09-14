package com.lemon.vmspinup.xml.storage;


import javax.xml.bind.annotation.*;

import org.eclipse.persistence.oxm.annotations.XmlPath;

@XmlRootElement(name = "volume")
@XmlAccessorType(XmlAccessType.FIELD)

public class Volume {

    @XmlAttribute(name = "type")
    private String type;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "key")
    private String key;

    @XmlElement(name = "allocation")
    private long allocation;
    
    @XmlPath("allocation/@unit")
    private String allocationUnit;
    
    @XmlElement(name = "capacity")
    private long capacity;
    
    @XmlPath("capacity/@unit")
    private String capacityUnit;
    
    @XmlElement(name = "backingStore")
    private BackingStore backingStore;
    
    @XmlElement(name = "target")
    private Target target;
    
    @XmlElement(name = "source")
    private Source source;
    
    public Volume() {
    }

    public String getName() {
        return name;
    }

    public Volume setKey(String key) {
        this.key = key;
        return this;
    }

    public Volume setAllocation(long allocation) {
        this.allocation = allocation;
        return this;
    }

    public String getAllocationUnit() {
        return allocationUnit;
    }

    public Volume setAllocationUnit(String allocationUnit) {
        this.allocationUnit = allocationUnit;
        return this;
    }

    public long getCapacity() {
        return capacity;
    }

    public Volume setCapacity(long capacity) {
        this.capacity = capacity;
        return this;
    }

    public String getCapacityUnit() {
        return capacityUnit;
    }

    public Volume setCapacityUnit(String capacityUnit) {
        this.capacityUnit = capacityUnit;
        return this;
    }

    public BackingStore getBackingStore() {
        return backingStore;
    }

    public Volume setBackingStore(BackingStore backingStore) {
        this.backingStore = backingStore;
        return this;
    }

    public Target getTarget() {
        return target;
    }

    public Volume setTarget(Target target) {
        this.target = target;
        return this;
    }

    public Source getSource() {
        return source;
    }

    public Volume setSource(Source source) {
        this.source = source;
        return this;
    }


    public Volume setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public Volume setType(String type) {
        this.type = type;
        return this;
    }

    public String getKey() {
        return key;
    }

    public long getAllocation() {
        return allocation;
    }
}
