#!/bin/bash
sudo apt-get install qemu-utils python-pip -y
git clone http://github.com/openstack/diskimage-builder
cd diskimage-builder
sudo pip install -r requirements.txt
