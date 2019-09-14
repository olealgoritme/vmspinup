package com.lemon.vmspinup.xml.storage;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "target")
@XmlAccessorType(XmlAccessType.FIELD)
public class Target {
    @XmlElement(name = "path")
    private String path;

    @XmlElement(name = "format")
    private String format;

    @XmlPath("format/@type")
    private String formatType;

    @XmlElement(name = "permissions")
    private Permissions permissions;

    public String getPath() {
        return this.path;
    }

    public Target setPath(String path) {
        this.path = path;
        return this;
    }

    public String getFormatType() {
        return formatType;
    }

    public Target setFormatType(String formatType) {
        this.formatType = formatType;
        return this;
    }

    public Permissions getPermissions() {
        return permissions;
    }

    public Target setPermissions(Permissions permissions) {
        this.permissions = permissions;
        return this;
    }
    
    
}
