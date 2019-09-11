#!/bin/bash
virsh pool-define pool-install-files.xml
virsh pool-define pool-instances.xml
virsh pool-define pool-templates.xml
