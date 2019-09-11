package com.lemon.vmspinup.xml.volume;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlPath;

/**
 * @author msimonin
 *
 */
@XmlRootElement(name = "info")
@XmlAccessorType(XmlAccessType.FIELD)
public class LibvirtConfigVolumeInfo 
{
    /** path.*/
    @XmlElement(name = "path")
    private String path;
    
    /** format.*/
    @XmlElement(name = "format")
    private String format;
    
    /** format type.*/
    @XmlPath("format/@type")
    private String formatType;

    /** permissions.*/
    @XmlElement(name = "permissions")
    private LibvirtConfigVolumePermissions permissions;

    /**
     * @return the path
     */
    public String getPath() 
    {
        return path;
    }

    /**
     * @param path the path to set
     * @return this
     */
    public LibvirtConfigVolumeInfo setPath(String path)
    {
        path = path;
        return this;
    }

    /**
     * @return the formatType
     */
    public String getFormatType() 
    {
        return formatType;
    }

    /**
     * @param formatType the formatType to set
     * @return this
     */
    public LibvirtConfigVolumeInfo setFormatType(String formatType)
    {
        formatType = formatType;
        return this;
    }

    /**
     * @return the permissions
     */
    public LibvirtConfigVolumePermissions getPermissions() 
    {
        return permissions;
    }

    /**
     * @param permissions the permissions to set
     * @return this
     */
    public LibvirtConfigVolumeInfo setPermissions(LibvirtConfigVolumePermissions permissions)
    {
        permissions = permissions;
        return this;
    }
    
    
}
