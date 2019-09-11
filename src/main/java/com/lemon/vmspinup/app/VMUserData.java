package com.lemon.vmspinup.app;

public class VMUserData {

    private String hostname;
    private String password;
    private String imagePath;

    public VMUserData(String imagePath) {
        this.imagePath= imagePath;
    }


    public VMUserData(String hostname, String password) {
        this.hostname = hostname;
        this.password = password;
    }
    public String toXML() {

        return "#cloud-config" + "\n" +
        "hostname: " + this.hostname + "\n" +
        "password: " + this.password + "\n" +
        "chpassword: { expire: False }" + "\n" +
        "ssh_pwauth: True";

    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
    /**
    *  Object class representing
     * user-data.img file
     * needs to be converted with 'cloud-init datasource'
     * ex: cloud-localds vm-instance-uuid-user-data.img user-data.cfg
     *
     *
     * File structure:
     * Minimum:
     * ----------------
     *  #cloud-config
     *  hostname: hostname
     *  password: password
     *  chpassword: { expire: False }
     *  ssh_pwauth: True
     * -----------------
     *
     * Optional:
     *
     * users:
     *   - name: root
     *     ssh_pwauth: True
     *     ssh_authorized_keys:
     *       - ssh-rsa AA..vz user@domain.com
     * bootcmd:
     *  - echo New MOTD >> /etc/motd
     * runcmd:
     *  - echo New MOTD2 >> /etc/motd
     */

}
