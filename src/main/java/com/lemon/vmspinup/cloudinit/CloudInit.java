package com.lemon.vmspinup.cloudinit;

import com.lemon.vmspinup.cloudinit.CloudInitConfig;
import com.lemon.vmspinup.cloudinit.CloudInitMetaData;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CloudInit {

    private CloudInitMetaData metaData;
    private CloudInitConfig cloudConfig;
    private String outputImageFile;

    public CloudInit(CloudInitConfig cloudConfig, CloudInitMetaData metaData, String outputImageFile) {
        this.cloudConfig = cloudConfig;
        this.metaData = metaData;
        this.outputImageFile = outputImageFile;
    }

    private boolean createFile(String fullPath, String text)  {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(fullPath));
            writer.write(text);
        } catch (IOException e) {
            e.printStackTrace();
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            return false;
        }

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean buildFiles() {
      if(metaData == null || cloudConfig == null) {
          throw new IllegalArgumentException("Need MetaData and CloudConfig objects");
      }

          // Step 1: create user-data.cfg + meta-data-cfg.
          // Step 2: create user-data.img (outputImageFile)
          // Step 3: "genisoimage  -output seed.iso -volid cidata -joliet -rock user-data meta-data"

        String metaDataFile = cloudConfig.getHostname() + "-user-meta.cfg";
        String cloudConfigFile = cloudConfig.getHostname() + "-user-data.cfg";

        // Step1: Metadata file
        if(createFile(metaDataFile, metaData.getMetaData())) {

            // Step2: Cloud-config
            if(createFile(cloudConfigFile, cloudConfig.getConfig())) {

                // Step3: genisoimage -- create final image and delete temp meta/cfg files
                ProcessBuilder builder = new ProcessBuilder();

                String cmd = "/usr/bin/sudo /usr/bin/cloud-localds " + outputImageFile + " " + cloudConfigFile + " " + metaDataFile
                        + " ; rm -f " + metaDataFile
                        + " ; rm -f " + cloudConfigFile;
                System.out.println("Running: " + cmd);
                builder.command("/bin/bash", "-c", cmd);
                try {
                    builder.start();
                } catch (IOException e) {
                    e.printStackTrace();
                    return false;
                }
            } else return false;
        } else return false;

        return true;
    }

    public CloudInitMetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(CloudInitMetaData metaData) {
        this.metaData = metaData;
    }

    public CloudInitConfig getCloudConfig() {
        return cloudConfig;
    }

    public void setCloudConfig(CloudInitConfig cloudConfig) {
        this.cloudConfig = cloudConfig;
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
