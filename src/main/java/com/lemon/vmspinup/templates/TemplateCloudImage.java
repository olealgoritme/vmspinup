package com.lemon.vmspinup.templates;

import java.io.Serializable;

public abstract class TemplateCloudImage implements Serializable {

    public TemplateCloudImage() {
    }


    public TemplateCloudImage buildXML() {
        return this;
    }


}
