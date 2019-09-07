package com.lemon.vmspinup.model.vm.templates;

import java.util.UUID;

public class StandardTemplate {


    public String buildTemplate (String name, UUID uuid, long memory,
                                 int vCpu, String vmImage) {
        String arch, machine, kernel, cmdline, dtb;

            arch    = "x86_64";
            machine = "pc-q35-3.1";
            cmdline = "";
            kernel  = "";
            dtb     = "";

        return Template.getTemplate(
                name, uuid.toString(), String.valueOf(memory),
                String.valueOf(vCpu), arch, machine, kernel,
                cmdline, dtb, vmImage);

    }

    public String buildBareMetalBoot() {
        String name =   "ubuntu-1";
        String mem =    "2097152";
        String vcpu =   "2";
        String qcow =   "/home/xuw/servers/test_bare_metal.qcow2";

        String XML =
                "<domain type='kvm'>"+ "\n" +
                        "<name>%s</name>"+  "\n" +
                        "<memory unit='KiB'>%s</memory>"+ "\n" +
                        "<vcpu>%s</vcpu>"+ "\n" +
                        "<os>"+ "\n" +
                        "<type arch='x86_64'>hvm</type>"+ "\n" +
                        "</os>"+ "\n" +
                        "<clock sync='localtime'/>"+ "\n" +
                        "<devices>"+ "\n" +
                        "<emulator>/usr/bin/qemu-system-x86_64</emulator>"+ "\n" +
                        "<disk type='file' device='disk'>"+ "\n" +
                        "<driver name='qemu' type='qcow2'/>"+ "\n" +
                        "<source file='%s'/>"+ "\n" +
                        "<target dev='vda' bus='virtio'/>"+ "\n" +
                        "<boot order='2'/>"+ "\n" +
                        "</disk>"+ "\n" +
                        "<interface type='network'>"+ "\n" +
                        "<source network='default'/>"+ "\n" +
                        "<mac address='24:42:53:21:52:45'/>"+ "\n" +
                        "</interface>"+ "\n" +
                        "<graphics type='vnc' port='-1' autoport='yes'/>"+ "\n" +
                        "</devices>"+ "\n" +
                        "</domain>";

        return String.format(XML, name, mem, vcpu, qcow);

    }

    public String buildBareMetalInstallation() {
        String name =   "bare-metal-test";
        String uuid = UUID.randomUUID().toString();
        String mem =    "4194304";
        String vcpu =   "4";
        String qcow =   "/home/xuw/servers/test_bare_metal.qcow2";
        String cdrom =  "/home/xuw/servers/ISO/ubuntu-18.04.3-server-amd64-unattended.iso";

        String XML =
"<domain type='kvm'>"+ "\n" +
"<name>%s</name>"+  "\n" +
        "<uuid>%s</uuid>"+ "\n" +
        "<memory unit='KiB'>%s</memory>"+ "\n" +
        "<vcpu>%s</vcpu>"+ "\n" +
        "<os>"+ "\n" +
        "<type arch='x86_64'>hvm</type>"+ "\n" +
        "</os>"+ "\n" +
        "<clock sync='localtime'/>"+ "\n" +
        "<devices>"+ "\n" +
        "<emulator>/usr/bin/qemu-system-x86_64</emulator>"+ "\n" +
        "<disk type='file' device='disk'>"+ "\n" +
        "<driver name='qemu' type='qcow2'/>"+ "\n" +
        "<source file='%s'/>"+ "\n" +
        "<target dev='vda' bus='virtio'/>"+ "\n" +
        "<boot order='2'/>"+ "\n" +
        "</disk>"+ "\n" +
        "<disk type='file' device='cdrom'>"+ "\n" +
        "<source file='%s'/>"+ "\n" +
        "<target dev='sda' bus='scsi'/>"+ "\n" +
        "<boot order='1'/>"+ "\n" +
        "<readonly/>"+ "\n" +
        "</disk>"+ "\n" +
        "<interface type='network'>"+ "\n" +
        "<source network='default'/>"+ "\n" +
        "<mac address='24:42:53:21:52:45'/>"+ "\n" +
        "</interface>"+ "\n" +
        "<graphics type='vnc' port='-1' autoport='yes'/>"+ "\n" +
        "</devices>"+ "\n" +
        "</domain>";

        return String.format(XML, name, uuid, mem, vcpu, qcow, cdrom);

    }
}
