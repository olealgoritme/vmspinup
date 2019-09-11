package com.lemon.vmspinup.xml.volume;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * LibvirtConfigVolumePermissions.
 * 
 * @author msimonin
 *
 */
@XmlRootElement(name = "permissions")
@XmlAccessorType(XmlAccessType.FIELD)
public class LibvirtConfigVolumePermissions
{
    /** owner.*/
    @XmlElement(name = "owner")
    private String owner;
    
    /** group.*/
    @XmlElement(name = "group")
    private String group;
    
    /** mode.*/
    @XmlElement(name = "mode")
    private String mode;
    
    /** label. */
    @XmlElement(name = "label")
    private String label;

    /**
     * @return the owner
     */
    public String getOwner() 
    {
        return owner;
    }

    /**
     * @param owner the owner to set
     */
    public void setOwner(String owner) 
    {
        owner = owner;
    }

    /**
     * @return the group
     */
    public String getGroup() 
    {
        return group;
    }

    /**
     * @param group the group to set
     * @return this
     */
    public LibvirtConfigVolumePermissions setGroup(String group) 
    {
        group = group;
        return this;
    }

    /**
     * @return the mode
     */
    public String getMode() 
    {
        return mode;
    }

    /**
     * @param mode the mode to set
     * @return this
     */
    public LibvirtConfigVolumePermissions setMode(String mode) 
    {
        mode = mode;
        return this;
    }

    /**
     * @return the label
     */
    public String getLabel() 
    {
        return label;
    }

    /**
     * @param label the label to set
     * @return this
     */
    public LibvirtConfigVolumePermissions setLabel(String label) 
    {
        label = label;
        return this;
    }
    
}
