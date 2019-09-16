#!/bin/bash
# Manually create directory pools
# Using /mnt/ as base directory
# - xuw@2019
# E.g: ./setup_pool.sh /mnt/templates/xml-templates/

# define
virsh pool-define $1pool-cloudimg-files.xml
virsh pool-define $1pool-iso-files.xml
virsh pool-define $1pool-templates.xml
virsh pool-define $1pool-vm-instances.xml

# build
virsh pool-build $1cloudimg-files
virsh pool-build $1iso-files
virsh pool-build $1templates
virsh pool-build $1vm-instances

# start
virsh pool-start $1pool-cloudimg-files
virsh pool-start $1pool-iso-files
virsh pool-start $1pool-templates
virsh pool-start $1pool-vm-instances

# autostart
virsh pool-autostart $1cloudimg-files
virsh pool-autostart $1iso-files
virsh pool-autostart $1templates
virsh pool-autostart $1vm-instances
