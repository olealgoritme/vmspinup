package com.lemon.vmspinup.xml.capabilities;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * @author msimonin
 *
 */
@XmlRootElement(name = "machine")
@XmlAccessorType(XmlAccessType.FIELD)
public class Machine {
    
    /** machine.*/
    @XmlValue
    private String machine;
    
    
    /** machine canonical. */
    @XmlAttribute(name = "canonical")
    private String machineCanonical;


    /**
     * @return the machine
     */
    public String getMachine() {
        return machine;
    }


    /**
     * @param machine the machine to set
     * @return this
     */
    public Machine setMachine(String machine) {
        machine = machine;
        return this;
    }


    /**
     * @return the machineCanonical
     */
    public String getMachineCanonical() {
        return machineCanonical;
    }


    /**
     * @param machineCanonical the machineCanonical to set
     * @return this
     */
    public Machine setMachineCanonical(String machineCanonical) {
        machineCanonical = machineCanonical;
        return this;
    }
    
}
