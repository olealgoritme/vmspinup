package com.lemon.vmspinup.app;

import com.lemon.vmspinup.xml.storage.Volume;

public class Config {

    private final static String TEMPLATES_PATH = "/mnt/templates";
    public final static String TEMPLATE_DEVICES_PATH = TEMPLATES_PATH + "/device";
    public final static String TEMPLATE_INTERFACES_PATH= TEMPLATES_PATH + "/interface";
    public final static String TEMPLATE_NETWORK_PATH = TEMPLATES_PATH + "/network";
    public final static String TEMPLATE_POOL_PATH = TEMPLATES_PATH + "/pool";
    public final static String TEMPLATE_STORAGE_PATH= TEMPLATES_PATH + "/storage";
    public final static String TEMPLATE_VM_PATH = TEMPLATES_PATH + "/vm";

    private final static String INSTANCES_PATH = "/mnt/instances";
    public final static String VM_INSTANCES_PATH = INSTANCES_PATH + "/vm";
    public final static String VM_USER_DATA_PATH = INSTANCES_PATH + "/vm-userdata";

    private final static String INSTALL_FILE_PATH = "/mnt/install-files";
    public final static String CLOUD_IMG_PATH = INSTALL_FILE_PATH + "/cloudimg";
    public final static String ISO_IMG_PATH = INSTALL_FILE_PATH + "/iso";
}
