package com.lemon.vmspinup.xml.vm;
import com.lemon.vmspinup.xml.storage.Disk;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;



@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "devices")
public class Devices
{
    /** emulator.*/
    @XmlElement(name = "emulator")
    private String emulator;
    
    /** disk.*/
    @XmlElement(name = "disk")
    private ArrayList<Disk> disks;
    
    /** interfaces.*/
    @XmlElement(name = "interface")
    private ArrayList<Interface> interfaces;
    
    /** serials.*/
    @XmlElement(name = "serial")
    private ArrayList<SerialConsole> serials;
    
    /** consoles.*/
    @XmlElement(name = "console")
    private ArrayList<SerialConsole> consoles;
    
    /** graphics.*/
    @XmlElement(name = "graphics")
    private ArrayList<Graphics> graphics;
    
    /**
     * Constructor. 
     */
    public Devices()
    {
        disks = new ArrayList<Disk>();
        interfaces = new ArrayList<Interface>();
        serials = new ArrayList<SerialConsole>();
        consoles = new ArrayList<SerialConsole>();
        graphics = new ArrayList<Graphics>();
    }

    /**
     * @return the disks
     */
    public ArrayList<Disk> getDisks()
    {
        return disks;
    }

    /**
     * @param disks the disks to set
     * @return this
     */
    public Devices setDisks(ArrayList<Disk> disks)
    {
        this.disks = disks;
        return this;
    }

    /**
     * @return the interfaces
     */
    public ArrayList<Interface> getInterfaces()
    {
        return interfaces;
    }

    /**
     * @param interfaces the interfaces to set
     * @return this
     */
    public Devices setInterfaces(ArrayList<Interface> interfaces)
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
    public Devices setEmulator(String emulator)
    {
        this.emulator = emulator;
        return this;
    }

    /**
     * @return the serials
     */
    public ArrayList<SerialConsole> getSerials()
    {
        return serials;
    }

    /**
     * @param serials the serials to set
     * @return this
     */
    public Devices setSerials(ArrayList<SerialConsole> serials)
    {
        this.serials = serials;
        return this;
    }

    /**
     * @return the consoles
     */
    public ArrayList<SerialConsole> getConsoles()
    {
        return consoles;
    }

    /**
     * @param consoles the consoles to set
     * @return this
     */
    public Devices setConsoles(ArrayList<SerialConsole> consoles)
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
    public Devices addDisk(Disk disk)
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
    public Devices addInterface(Interface interf)
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
    public Devices addSerial(SerialConsole serial)
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
    public Devices addConsole(SerialConsole console)
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
    public Devices addGraphics(Graphics graphics)
    {
        getGraphics().add(graphics);
        return this;
    }

    /**
     * @return the graphics
     */
    public ArrayList<Graphics> getGraphics()
    {
        return graphics;
    }

   
    /**
     * @param graphics  The graphics to set.
     * @return  this.
     */
    public Devices setGraphics(ArrayList<Graphics> graphics)
    {
        this.graphics = graphics;
        return this;
    }
}
