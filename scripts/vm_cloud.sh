#!/bin/bash

#sudo apt-get install cloud-image-utils qemu

# This is already in qcow2 format.
folder="VMs/"
img="ubuntu-18.04-server-cloudimg-amd64.img"
filepath="$folder$img"
if [ ! -f "$filepath" ]; then
      wget -P $folder "https://cloud-images.ubuntu.com/releases/18.04/release/${img}"
        # sparse resize: does not use any extra space, just allows the resize to happen later on.
          # https://superuser.com/questions/1022019/how-to-increase-size-of-an-ubuntu-cloud-image
            qemu-img resize "$filepath" +128G
        fi

        vm_config=config.img
        if [ ! -f "$vm_config" ]; then
              # For the password.
                # https://stackoverflow.com/questions/29137679/login-credentials-of-ubuntu-cloud-server-image/53373376#53373376
                  # https://serverfault.com/questio/40ns/920117/how-do-i-set-a-password-on-an-ubuntu-cloud-image/940686#940686
                    # https://askubuntu.com/questions/507345/how-to-set-a-password-for-ubuntu-cloud-images-ie-not-use-ssh/1094189#1094189
                        cloud-localds "$vm_config" vm_config.cfg
        fi


qemu-system-x86_64 \
-drive "file=${filepath},format=qcow2" \
-drive "file=${vm_config},format=raw" \
-device rtl8139,netdev=net0 \
-enable-kvm \
-m 1G \
-netdev user,id=net0 \
-serial mon:stdio \
-smp 1 \
-vga virtio

