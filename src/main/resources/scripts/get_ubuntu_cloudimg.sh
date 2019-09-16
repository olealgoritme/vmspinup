## Install a necessary packages
sudo apt-get install kvm cloud-utils genisoimage

## URL to most recent cloud image of 12.04
img_url="https://cloud-images.ubuntu.com/releases/bionic/release/"
img_url="${img_url}/ubuntu-18.04-server-cloudimg-amd64.img"
## download the image
wget $img_url
