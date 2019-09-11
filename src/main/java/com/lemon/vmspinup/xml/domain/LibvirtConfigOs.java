package com.lemon.vmspinup.xml.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

/**
 * 
 * Os section.
 * 
 * @author msimonin
 *
 */
@XmlRootElement(name = "os")
@XmlAccessorType(XmlAccessType.FIELD)
public class LibvirtConfigOs 
{

    /** os type. */
    @XmlElement(name = "type")
    private String type;
    
    /** os arch.*/
    @XmlPath("type/@arch")
    private String typeArch;
    
    /** os machine.*/
    @XmlPath("type/@machine")
    private String typeMachine;

    /** boot.*/
    @XmlElement(name = "boot")
    private String boot;
    
    /** boot dev. */
    @XmlPath("boot/@dev")
    private String bootDev;
    

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
    public LibvirtConfigOs setType(String type)
    {
        type = type;
        return this;
    }


    /**
     * @return the typeArch
     */
    public String getTypeArch()
    {
        return typeArch;
    }


    /**
     * @param typeArch the typeArch to set
     * @return this
     */
    public LibvirtConfigOs setTypeArch(String typeArch)
    {
        typeArch = typeArch;
        return this;
    }


    /**
     * @return the typeMachine
     */
    public String getTypeMachine()
    {
        return typeMachine;
    }


    /**
     * @param typeMachine the typeMachine to set
     * @return this
     */
    public LibvirtConfigOs setTypeMachine(String typeMachine)
    {
        typeMachine = typeMachine;
        return this;
    }



    /**
     * @return the bootDev
     */
    public String getBootDev()
    {
        return bootDev;
    }


    /**
     * @param bootDev the bootDev to set
     * @return this
     */
    public LibvirtConfigOs setBootDev(String bootDev)
    {
        bootDev = bootDev;
        return this;
    }

    
    
    
    
    
}
