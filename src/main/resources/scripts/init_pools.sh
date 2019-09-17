#!/binb/bash

virsh pool-define pool-cloudimg-files.xml
virsh pool-define pool-iso-files.xml
virsh pool-define pool-netfs.xml
virsh pool-define pool-templates.xml
virsh pool-define pool-vm-instances.xml
virsh pool-define pool-vm-userdata.xml


#build
virsh pool-build pool-cloudimg-files.xml
virsh pool-build pool-iso-files.xml
virsh pool-build pool-netfs.xml
virsh pool-build pool-templates.xml
virsh pool-build pool-vm-instances.xml
virsh pool-build pool-vm-userdata.xml


#start
virsh pool-start pool-cloudimg-files
virsh pool-start pool-iso-files
virsh pool-start pool-netfs
virsh pool-start pool-templates
virsh pool-start pool-vm-instances
virsh pool-start pool-vm-userdata


#start
virsh pool-autostart pool-cloudimg-files
virsh pool-autostart pool-iso-files
virsh pool-autostart pool-netfs
virsh pool-autostart pool-templates
virsh pool-autostart pool-vm-instances
virsh pool-autostart pool-vm-userdata

