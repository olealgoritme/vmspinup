#!/bin/bash
# Automatically directory pools
# Using /mnt/ as base directory
# Sets CHOWN to libvirtd's user and kvm group
# - xuw@2019
DIR=("../xml-templates/pool")
LIBVIRT_USER="libvirt"
KVM_GROUP="kvm"
# TODO: set directory permissions to libvirt:kvm/qemu

# define
virsh pool-define $DIR/pool-cloudimg-files.xml
virsh pool-define $DIR/pool-iso-files.xml
virsh pool-define $DIR/pool-templates.xml
virsh pool-define $DIR/pool-vm-instances.xml

# build
virsh pool-build cloudimg-files
virsh pool-build iso-files
virsh pool-build templates
virsh pool-build vm-instances

# start
virsh pool-start cloudimg-files
virsh pool-start iso-files
virsh pool-start templates
virsh pool-start vm-instances

# autostart
virsh pool-autostart cloudimg-files
virsh pool-autostart iso-files
virsh pool-autostart templates
virsh pool-autostart vm-instances
