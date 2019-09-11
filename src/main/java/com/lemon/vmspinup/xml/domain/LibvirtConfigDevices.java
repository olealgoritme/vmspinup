package com.lemon.vmspinup.xml.domain;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



/**
 * 
 * LibvirtConfigDevice.
 * 
 * @author msimonin
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "devices")
public class LibvirtConfigDevices
{
    /** emulator.*/
    @XmlElement(name = "emulator")
    private String emulator;
    
    /** disk.*/
    @XmlElement(name = "disk")
    private ArrayList<LibvirtConfigDisk> disks;
    
    /** interfaces.*/
    @XmlElement(name = "interface")
    private ArrayList<LibvirtConfigInterface> interfaces;
    
    /** serials.*/
    @XmlElement(name = "serial")
    private ArrayList<LibvirtConfigSerialConsole> serials;
    
    /** consoles.*/
    @XmlElement(name = "console")
    private ArrayList<LibvirtConfigSerialConsole> consoles;
    
    /** graphics.*/
    @XmlElement(name = "graphics")
    private ArrayList<LibvirtConfigGraphics> graphics;
    
    /**
     * Constructor. 
     */
    public LibvirtConfigDevices()
    {
        disks = new ArrayList<LibvirtConfigDisk>();
        interfaces = new ArrayList<LibvirtConfigInterface>();
        serials = new ArrayList<LibvirtConfigSerialConsole>();
        consoles = new ArrayList<LibvirtConfigSerialConsole>();
        graphics = new ArrayList<LibvirtConfigGraphics>();
    }

    /**
     * @return the disks
     */
    public ArrayList<LibvirtConfigDisk> getDisks()
    {
        return disks;
    }

    /**
     * @param disks the disks to set
     * @return this
     */
    public LibvirtConfigDevices setDisks(ArrayList<LibvirtConfigDisk> disks)
    {
        this.disks = disks;
        return this;
    }

    /**
     * @return the interfaces
     */
    public ArrayList<LibvirtConfigInterface> getInterfaces()
    {
        return interfaces;
    }

    /**
     * @param interfaces the interfaces to set
     * @return this
     */
    public LibvirtConfigDevices setInterfaces(ArrayList<LibvirtConfigInterface> interfaces)
    {
        this.interfaces = interfaces;
        return this;
    }

    /**
     * @return the emulator
     */
    public String getEmulator()
    {
        return emulator;
    }

    /**
     * @param emulator the emulator to set
     * @return this
     */
    public LibvirtConfigDevices setEmulator(String emulator)
    {
        this.emulator = emulator;
        return this;
    }

    /**
     * @return the serials
     */
    public ArrayList<LibvirtConfigSerialConsole> getSerials()
    {
        return serials;
    }

    /**
     * @param serials the serials to set
     * @return this
     */
    public LibvirtConfigDevices setSerials(ArrayList<LibvirtConfigSerialConsole> serials)
    {
        this.serials = serials;
        return this;
    }

    /**
     * @return the consoles
     */
    public ArrayList<LibvirtConfigSerialConsole> getConsoles()
    {
        return consoles;
    }

    /**
     * @param consoles the consoles to set
     * @return this
     */
    public LibvirtConfigDevices setConsoles(ArrayList<LibvirtConfigSerialConsole> consoles)
    {
        this.consoles = consoles;
        return this;
    }

    /**
     * 
     * Add a disk.
     * 
     * @param disk  the disk to add.
     * @return this
     */
    public LibvirtConfigDevices addDisk(LibvirtConfigDisk disk) 
    {
        getDisks().add(disk);
        return this;
    }

    /**
     * 
     * Add an interface.
     *  
     * @param interf    the interface to add.
     * @return this
     */
    public LibvirtConfigDevices addInterface(LibvirtConfigInterface interf) 
    {
        getInterfaces().add(interf);
        return this;
    }

    /**
     * 
     * Add a serial.
     * 
     * @param serial    the serial to add.
     * @return this
     */
    public LibvirtConfigDevices addSerial(LibvirtConfigSerialConsole serial) 
    {
        getSerials().add(serial);
        return this;
    }
    
    /**
     * 
     * Add a console.
     * 
     * @param console    the console to add.
     * @return this
     */
    public LibvirtConfigDevices addConsole(LibvirtConfigSerialConsole console) 
    {
        getSerials().add(console);
        return this;
    }

    /**
     * 
     * Add a graphics section.
     * 
     * @param graphics  the graphics to add.
     * @return  this.
     */
    public LibvirtConfigDevices addGraphics(LibvirtConfigGraphics graphics)
    {
        getGraphics().add(graphics);
        return this;
    }

    /**
     * @return the graphics
     */
    public ArrayList<LibvirtConfigGraphics> getGraphics() 
    {
        return graphics;
    }

   
    /**
     * @param graphics  The graphics to set.
     * @return  this.
     */
    public LibvirtConfigDevices setGraphics(ArrayList<LibvirtConfigGraphics> graphics) 
    {
        this.graphics = graphics;
        return this;
    }
}
