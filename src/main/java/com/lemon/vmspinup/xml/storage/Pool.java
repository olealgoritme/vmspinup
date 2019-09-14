package com.lemon.vmspinup.xml.storage;


import org.eclipse.persistence.oxm.annotations.XmlPath;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "pool")
@XmlAccessorType(XmlAccessType.FIELD)

public class Pool {

    @XmlAttribute(name = "type")
    private String type;

    @XmlElement(name = "name")
    private String name;

    @XmlElement(name = "uuid")
    private String uuid;

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

    public Pool() {
    }

    public String getName() {
        return name;
    }

    public Pool setAllocation(long allocation) {
        this.allocation = allocation;
        return this;
    }

    public String getAllocationUnit() {
        return allocationUnit;
    }

    public Pool setAllocationUnit(String allocationUnit) {
        this.allocationUnit = allocationUnit;
        return this;
    }

    public long getCapacity() {
        return capacity;
    }

    public Pool setCapacity(long capacity) {
        this.capacity = capacity;
        return this;
    }

    public String getCapacityUnit() {
        return capacityUnit;
    }

    public Pool setCapacityUnit(String capacityUnit) {
        this.capacityUnit = capacityUnit;
        return this;
    }

    public BackingStore getBackingStore() {
        return backingStore;
    }

    public Pool setBackingStore(BackingStore backingStore) {
        this.backingStore = backingStore;
        return this;
    }

    public Target getTarget() {
        return target;
    }

    public Pool setTarget(Target target) {
        this.target = target;
        return this;
    }

    public Source getSource() {
        return source;
    }

    public Pool setSource(Source source) {
        this.source = source;
        return this;
    }


    public Pool setName(String name) {
        this.name = name;
        return this;
    }

    public String getType() {
        return type;
    }

    public Pool setType(String type) {
        this.type = type;
        return this;
    }


    public long getAllocation() {
        return allocation;
    }
}
