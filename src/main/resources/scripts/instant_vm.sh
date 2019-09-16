## Install a necessary packages
sudo apt-get install kvm cloud-utils genisoimage

## URL to most recent cloud image of 12.04
img_url="https://cloud-images.ubuntu.com/releases/bionic/release/"
img_url="${img_url}/ubuntu-18.04-server-cloudimg-amd64.img"
## download the image
wget $img_url -O ubuntu.img

## Create a file with some user-data in it
cat > user-data.cfg <<EOF
#cloud-config
hostname: ubuntu1
password: 1234
chpasswd: { expire: False }
ssh_pwauth: True
EOF

## Convert the compressed qcow file downloaded to a uncompressed qcow2
#qemu-img convert -O qcow2 disk.img.dist disk.img.orig

## create the disk with NoCloud data on it.
cloud-localds user-data.img user-data.cfg

## Create a delta disk to keep our .orig file pristine
#qemu-img create -f qcow2 -b disk.img.orig disk.img

## Boot a kvm
kvm -net nic -net user -hda ubuntu.img -hdb user-data.img -m 512