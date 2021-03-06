package com.lemon.vmspinup.model.storage;

public class VMStorageVolume {
/*

    raw: a plain file
    bochs: Bochs disk image format
    cloop: compressed loopback disk image format
    cow: User Mode Linux disk image format
    dmg: Mac disk image format
    iso: CDROM disk image format
    qcow: QEMU v1 disk image format
    qcow2: QEMU v2 disk image format
    qed: QEMU Enhanced Disk image format
    vmdk: VMware disk image format
    vpc: VirtualPC disk image format

 */
    private Format format = Format.QCOW2;

    private Driver driver = Driver.qemu;

    public VMStorageVolume() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAutostart() {
        return autostart;
    }

    public void setAutostart(boolean autostart) {
        this.autostart = autostart;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format DEFAULT_FORMAT) {
        this.format = DEFAULT_FORMAT;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public enum Driver {
        qemu
    }
    public enum Format {
        BOCHS ("bochs"),
        CLOOP("loop"),
        DMG("dmg"),
        ISO("iso"),
        QED("qed"),
        VPC("vpc"),
        COW ("cow"),
        QCOW ("qcow"),
        QCOW2 ("qcow2"),
        RAW ("img"),
        SPARSE ("img"),
        VMDK ("img");

        private String extension;
        /**
         * @param extension The extension used in the storage volume file
         */
        Format(final String extension) {
            this.extension = extension;
        }

        public String getExtension() {
            return extension;
        }
    }

    private String path;
    private String key;
    private String name;
    private boolean autostart;

    public VMStorageVolume(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    /*
    TODO: Implement dynamic versions programmatically

<volume type="file">
  <name>cow.img</name>
  <storage>
    <allocation>0</allocation>
    <capacity unit="T">1</capacity>
  </storage>
  <target>
    <format type="cow"/>
  </target>
</volume>


----------------------------------------
<volume type="file">
  <name>qcow2.img</name>
  <storage>
    <allocation>0</allocation>
    <capacity unit="T">1</capacity>
  </storage>
  <target>
    <format type="qcow2"/>
  </target>
</volume>


----------------------------------------
<volume type="file">
  <name>qcow.img</name>
  <storage>
    <allocation>0</allocation>
    <capacity unit="T">1</capacity>
  </storage>
  <target>
    <format type="qcow"/>
  </target>
</volume>


----------------------------------------
<volume type="file">
  <name>raw.img</name>
  <storage>
    <allocation unit="M">10</allocation>
    <capacity unit="M">1000</capacity>
  </storage>
</volume>

----------------------------------------
<volume type="file">
  <name>sparse.img</name>
  <storage>
    <allocation>0</allocation>
    <capacity unit="T">1</capacity>
  </storage>
</volume>


----------------------------------------
<volume type="file">
  <name>vmdk3.img</name>
  <storage>
    <allocation>0</allocation>
    <capacity unit="T">1</capacity>
  </storage>
  <target>
    <format type="vmdk"/>
  </target>
</volume>

    * */
}
