package com.lemon.vmspinup.xml.domain;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;
/**
 * 
 * Disk config.
 * @author msimonin
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "disk")
public class LibvirtConfigDisk
{
    /** Device.*/
    @XmlAttribute(name = "device")
    private String device;
   
    /** type.*/
   @XmlAttribute(name = "type")
   private String type;

   /** driver.*/
   @XmlElement(name = "driver")
   private String driver;
   
   /** driver name.*/ 
   @XmlPath("driver/@name")
   private String driverName;
   
   /** driver type.*/
   @XmlPath("driver/@type")
   private String driverType;
   
   /** Cache type.*/
   @XmlPath("driver/@cache")
   private String driverCache;
   
   
   /** source.*/
   @XmlElement(name = "source")
   private String source;
   
   /** souce file.*/
   @XmlPath("source/@file")
   private String sourceFile;
   
   /** address.*/
   @XmlElement(name = "address")
   private LibvirtConfigAddress address;
   
   /** target.*/
   @XmlElement(name = "target")
   private String target;
   
   /** target bus.*/
   @XmlPath("target/@bus")
   private String targetBus;
   
   /** target dev.*/
   @XmlPath("target/@dev")
   private String targetDev;
  
   /**
    * Constructor.
    */
   public LibvirtConfigDisk()
   {
       device = "disk";
       type = "file";
   }
   
    /**
     * @return the device
     */
    public String getDevice()
    {
        return device;
    }

/**
 * @param device the device to set
 * @return this 
 */
public LibvirtConfigDisk setDevice(String device)
{
    this.device = device;
    return this;
}

/**
 * @return the type
 */
public String getType()
{
    return type;
}

/**
 * @param type the type to set
 * @return this
 */
public LibvirtConfigDisk setType(String type)
{
    this.type = type;
    return this;
}

/**
 * @return the driverName
 */
public String getDriverName()
{
    return driverName;
}

/**
 * @param driverName the driverName to set
 * @return this
 */
public LibvirtConfigDisk setDriverName(String driverName)
{
    this.driverName = driverName;
    return this;
}

/**
 * @return the driverType
 */
public String getDriverType()
{
    return driverType;
}

/**
 * @param driverType the driverType to set
 * @return this
 */
public LibvirtConfigDisk setDriverType(String driverType)
{
    this.driverType = driverType;
    return this;
}

/**
 * @return the sourceFile
 */
public String getSourceFile()
{
    return sourceFile;
}

/**
 * @param sourceFile the sourceFile to set
 * @return this
 */
public LibvirtConfigDisk setSourceFile(String sourceFile)
{
    this.sourceFile = sourceFile;
    return this;
}

/**
 * @return the targetBus
 */
public String getTargetBus()
{
    return targetBus;
}

/**
 * @param targetBus the targetBus to set
 * @return this
 */
public LibvirtConfigDisk setTargetBus(String targetBus)
{
    this.targetBus = targetBus;
    return this;
}

/**
 * @return the targetDev
 */
public String getTargetDev()
{
    return targetDev;
}

/**
 * @param targetDev the targetDev to set
 * @return this
 */
public LibvirtConfigDisk setTargetDev(String targetDev)
{
    this.targetDev = targetDev;
    return this;
}

/**
 * @return the driverCache
 */
public String getDriverCache() 
{
    return driverCache;
}

/**
 * @param driverCache the driverCache to set
 */
public LibvirtConfigDisk setDriverCache(String driverCache) 
{
    this.driverCache = driverCache;
    return this;
}
   
   
    
    
}
