package com.lemon.vmspinup.model.storage;

import java.util.UUID;

public class StoragePool {

    private String name;
    private UUID uuid;
    private long allocation;
    private long capacity;
    private long available;

    /*
TODO: Implement dynamic
pool-directory.xml:
-------------------------------------
<pool type="dir">
  <name>virtimages</name>
  <target>
    <path>/var/lib/virt/images</path>
  </target>
</pool>
-------------------------------------


pool-filesystem.xml:
-------------------------------------
<pool type="fs">
  <name>virtimages</name>
  <source>
    <device path="/dev/VolGroup00/VirtImages"/>
  </source>
  <target>
    <path>/var/lib/virt/images</path>
    <permissions>
      <mode>0700</mode>
      <owner>0</owner>
      <group>0</group>
      <label>system_u:object_r:xen_image_t:s0</label>
    </permissions>
  </target>
</pool>
-------------------------------------

pool-logical.xml:
-------------------------------------
<pool type="logical">
  <name>HostVG</name>
  <source>
    <device path="/dev/sda1"/>
  </source>
  <target>
    <path>/dev/HostVG</path>
  </target>
</pool>
-------------------------------------

pool-netfs.xml:

-------------------------------------
<pool type="netfs">
  <name>virtimages</name>
  <source>
    <host name="nfs.example.com"/>
    <directory path="/var/lib/virt/images"/>
  </source>
  <target>
    <path>/var/lib/virt/images</path>
    <permissions>
      <mode>0700</mode>
      <owner>0</owner>
      <group>0</group>
      <label>system_u:object_r:xen_image_t:s0</label>
    </permissions>
  </target>
</pool>
-------------------------------------

 */

    public static enum TYPE {
        DIRECTORY,
        FS,
        LOGICAL,
        NETFS
    }

}
