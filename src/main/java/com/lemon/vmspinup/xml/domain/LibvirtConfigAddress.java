package com.lemon.vmspinup.xml.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;



/**
 * 
 * Disk config.
 * @author msimonin
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "address")
public class LibvirtConfigAddress
{
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
