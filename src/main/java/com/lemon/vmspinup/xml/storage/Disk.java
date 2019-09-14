package com.lemon.vmspinup.xml.storage;
import org.eclipse.persistence.oxm.annotations.XmlPath;

import javax.xml.bind.annotation.*;
import java.io.Serializable;

@XmlRootElement(name = "disk")

@XmlType(propOrder = {"driver", "source", "address", "target", "boot" })
@XmlAccessorType(XmlAccessType.FIELD)
public class Disk implements Serializable {

    @XmlAttribute
    private String type;

    @XmlAttribute
    private String device;

    @XmlElement(name = "driver")
    private String driver;
   
    @XmlPath("driver/@name")
    private String driverName;

    @XmlPath("driver/@type")
    private String driverType;

    @XmlPath("driver/@cache")
    private String driverCache;

    @XmlElement(name = "source")
    private String source;

    @XmlPath("source/@file")
    private String sourceFile;
   
    private DiskAddress address;

    @XmlElement(name = "target")
    private String target;

    @XmlPath("target/@bus")
    private String targetBus;

    @XmlPath("target/@dev")
    private String targetDev;

    @XmlElement(name = "boot")
    private String boot;

    @XmlPath("boot/@order")
    private String bootOrder;

    public Disk() {
       this.device = "disk";
       this.type = "file";
    }
   
    public String getType() {
        return type;
    }

    public Disk setType(String type) {
        this.type = type;
        return this;
    }

    public String getDriverName() {
        return driverName;
    }

public Disk setDriverName(String driverName) {
    this.driverName = driverName;
    return this;
}

public String getDriverType() {
    return driverType;
}

public Disk setDriverType(String driverType) {
    this.driverType = driverType;
    return this;
}

public String getSourceFile() {
    return sourceFile;
}

public Disk setSourceFile(String sourceFile) {
    this.sourceFile = sourceFile;
    return this;
}

public String getTargetBus()
{
    return targetBus;
}

public Disk setTargetBus(String targetBus) {
    this.targetBus = targetBus;
    return this;
}

public String getTargetDev() {
    return targetDev;
}

public Disk setTargetDev(String targetDev) {
    this.targetDev = targetDev;
    return this;
}

public String getDriverCache() {
    return driverCache;
}

public Disk setDriverCache(String driverCache) {
    this.driverCache = driverCache;
    return this;
}


    public String getDriver() {
        return driver;
    }

    public Disk setDriver(String driver) {
        this.driver = driver;
        return this;
    }

    public String getSource() {
        return source;
    }

    public Disk setSource(String source) {
        this.source = source;
        return this;
    }

    public DiskAddress getAddress() {
        return address;
    }

    public Disk setAddress(DiskAddress address) {
        this.address = address;
        return this;
    }

    public String getTarget() {
        return target;
    }

    public Disk setTarget(String target) {
        this.target = target;
        return this;
    }

    public String getDevice() {
        return device;
    }

    public Disk setDevice(String device) {
        this.device = device;
        return this;
    }

    public String getBoot() {
        return boot;
    }

    public Disk setBoot(String boot) {
        this.boot = boot;
        return this;
    }

    public String getBootOrder() {
        return bootOrder;
    }

    public Disk setBootOrder(String bootOrder) {
        this.bootOrder = bootOrder;
        return this;
    }

    /* inner Disk.Address class */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlRootElement(name = "address")
    private static class DiskAddress  {
        /** Device.*/
        @XmlAttribute(name = "bus")
        private String bus;

        /** type.*/
        @XmlAttribute(name = "domain")
        private String domain;

        /** function.*/
        @XmlAttribute(name = "function")
        private String function;

        /** slot.*/
        @XmlAttribute(name = "slot")
        private String slot;

        /** type.*/
        @XmlAttribute(name = "type")
        private String type;

        /**
         * @return the bus
         */
        public String getBus()
        {
            return bus;
        }

        /**
         * @param bus the bus to set
         */
        public void setBus(String bus)
        {
            this.bus = bus;
        }

        /**
         * @return the domain
         */
        public String getDomain()
        {
            return domain;
        }

        /**
         * @param domain the domain to set
         */
        public void setDomain(String domain)
        {
            this.domain = domain;
        }

        /**
         * @return the function
         */
        public String getFunction()
        {
            return function;
        }

        /**
         * @param function the function to set
         */
        public void setFunction(String function)
        {
            this.function = function;
        }

        /**
         * @return the slot
         */
        public String getSlot() {
            return slot;
        }

        /**
         * @param slot the slot to set
         */
        public void setSlot(String slot) {
            slot = slot;
        }

        /**
         * @return the type
         */
        public String getType() {
            return type;
        }

        /**
         * @param type the type to set
         */
        public void setType(String type) {
            type = type;
        }

    }


}
