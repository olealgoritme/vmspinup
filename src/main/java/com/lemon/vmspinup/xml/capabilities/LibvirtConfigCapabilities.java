package com.lemon.vmspinup.xml.capabilities;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "capabilities")
@XmlAccessorType(XmlAccessType.FIELD)
public class LibvirtConfigCapabilities {
    /** host.*/
    @XmlElement(name = "host")
    private LibvirtConfigHost host;
    
    /** guests.*/
    @XmlElement(name = "guest")
    private ArrayList<LibvirtConfigGuest> guests;

    /**
     * 
     */
    public LibvirtConfigCapabilities() {
        host = new LibvirtConfigHost();
        guests = new ArrayList<LibvirtConfigGuest>();
    }

    /**
     * @return the host
     */
    public LibvirtConfigHost getHost() {
        return host;
    }

    /**
     * @param host the host to set
     * @return this
     */
    public LibvirtConfigCapabilities setHost(LibvirtConfigHost host) {
        host = host;
        return this;
    }

    /**
     * @return the guests
     */
    public ArrayList<LibvirtConfigGuest> getGuests() {
        return guests;
    }

    /**
     * @param guests the guests to set
     * @return this
     */
    public LibvirtConfigCapabilities setGuests(ArrayList<LibvirtConfigGuest> guests) {
        guests = guests;
        return this;
    }
    
    
}
