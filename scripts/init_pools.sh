#!/bin/bash

#define
virsh pool-define pool-install-files.xml
virsh pool-define pool-instances.xml
virsh pool-define pool-templates.xml


# start
virsh pool-start install-files
virsh pool-start instances
virsh pool-start templates

# autostrat

virsh pool-autostart install-files
virsh pool-autostart instances
virsh pool-autostart templates
