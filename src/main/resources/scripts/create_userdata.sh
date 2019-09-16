## Create a file with some user-data in it
cat > user-data.cfg <<EOF
#cloud-config
hostname: $1 
password: 1234
chpasswd: { expire: False }
ssh_pwauth: True
EOF
cloud-localds $1-user-data.img user-data.cfg
