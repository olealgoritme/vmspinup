package com.lemon.vmspinup.cloudinit;

public class CloudInitMetaData {

    private String instanceId;
    private String localHostname;

    public CloudInitMetaData(String instanceId, String localHostname) {
        this.instanceId = instanceId;
        this.localHostname = localHostname;
    }

    public CloudInitMetaData() {
    }

    public String getMetaData() {
        return
                "instance-id: " + instanceId + "\n" +
                        "local-hostname: " + localHostname;
    }

    public void setInstanceId(String instanceId) {
        this.instanceId = instanceId;
    }

    public void setLocalHostname(String localHostname) {
        this.localHostname = localHostname;
    }

    public String getInstanceId() {
        return instanceId;
    }

    public String getLocalHostname() {
        return localHostname;
    }
}
