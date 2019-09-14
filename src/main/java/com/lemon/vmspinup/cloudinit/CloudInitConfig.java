package com.lemon.vmspinup.cloudinit;

public class CloudInitConfig {

    private String hostname;
    private String password;


    public CloudInitConfig(String hostname, String password) {
        this.hostname = hostname;
        this.password = password;
    }

    public CloudInitConfig() {

    }

    public String getConfig() {
        return
                "#cloud-config" + "\n" +
                        "hostname: " + this.hostname + "\n" +
                        "password: " + this.password + "\n" +
                        "chpassword: { expire: False }" + "\n" +
                        "ssh_pwauth: True";
    }

    public String getHostname() {
        return hostname;
    }

    public String getPassword() {
        return password;
    }
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
