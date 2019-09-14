package com.lemon.vmspinup.xml.capabilities;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "capabilities")
@XmlAccessorType(XmlAccessType.FIELD)
public class Capabilities {
    /** host.*/
    @XmlElement(name = "host")
    private Host host;
    
    /** guests.*/
    @XmlElement(name = "guest")
    private ArrayList<Guest> guests;

    /**
     * 
     */
    public Capabilities() {
        host = new Host();
        guests = new ArrayList<Guest>();
    }

    /**
     * @return the host
     */
    public Host getHost() {
        return host;
    }

    /**
     * @param host the host to set
     * @return this
     */
    public Capabilities setHost(Host host) {
        host = host;
        return this;
    }

    /**
     * @return the guests
     */
    public ArrayList<Guest> getGuests() {
        return guests;
    }

    /**
     * @param guests the guests to set
     * @return this
     */
    public Capabilities setGuests(ArrayList<Guest> guests) {
        guests = guests;
        return this;
    }
    
    
}
