package model.vm.templates;

public class Template {

    private static String KERNEL_ENTRY  = "<kernel>%s</kernel>";
    private static String CMDLINE_ENTRY = "<cmdline>%s</cmdline>";
    private static String DTB_ENTRY     = "<dtb>%s</dtb> ";

    private static String TEMPLATE =
            "<domain type='kvm'>" +
                    "<name>%s</name>" +
                    "<uuid>%s</uuid>" +
                    "<memory>%s</memory>" +
                    "<vcpu>%sunit</vcpu>" +
                    "<os>" +
                    "<type arch='%s' machine='%s'>hvm</type>" +
                    "<boot dev='hd'/>" +
                    "%s" +
                    "%s" +
                    "%s" +
                    "</os>" +
                    "<clock offset='utc'/>" +
                    "<on_poweroff>destroy</on_poweroff>" +
                    "<on_reboot>restart</on_reboot>" +
                    "<on_crash>destroy</on_crash>" +
                    "<devices>" +
                        "<emulator>/usr/bin/qemu-system-x86_64</emulator>" +
                        "<disk type='file' device='disk'>" +
                            "<source file='%s'/>" +
                            "<driver name='qemu' type='qcow2'/>" +
                            "<target dev='vda' bus='virtio'/>" +
                            "<boot order='2'/>" +
                            "<address type='pci' domain='0x0000' bus='0x03' slot='0x00' function='0x0'/>" +
                        "</disk>" +
                    "<interface type='network'>" +
                    "<mac address='52:54:00:6a:84:e9'/>" +
                    "<source network='default'/>" +
                    "<target dev='vnet0'/>" +
                    "<alias name='net0'/>" +
                    "<address type='pci' domain='0x0000' bus='0x00' slot='0x03' function='0x0'/>" +
                    "</interface>" +
                    "<input type='mouse' bus='ps2'/>" +
                    "<graphics type='vnc' port='5900' autoport='yes'/>" +
                    "<video>" +
                    "<model type='cirrus' vram='9216' heads='1'/>" +
                    "<alias name='video0'/>" +
                    "<address type='pci' domain='0x0000' bus='0x00' slot='0x02' function='0x0'/>" +
                    "</video>" +
                    "</devices>" +
                    "</domain>";

    public static  String getTemplate(String vmName,   String vmUuid,
                                      String vmMemory, String vmCpu,
                                      String arch,     String machine,
                                      String kernel,   String cmdline,
                                      String dtb,      String vmImage) {
        String kernelEntry =
                (kernel == null || kernel.equals(""))?
                        "" : String.format(KERNEL_ENTRY, kernel);
        String cmdlineEntry =
                (cmdline == null || cmdline.equals(""))?
                        "" : String.format(CMDLINE_ENTRY, kernel);
        String dtbEntry = (dtb == null || dtb.equals(""))?
                "" : String.format(DTB_ENTRY, kernel);

        return String.format(
                TEMPLATE, vmName, vmUuid, vmMemory, vmCpu, arch, machine,
                kernelEntry, cmdlineEntry, dtbEntry, vmImage);
    }


    /*

    <domain type='kvm'>
  <name>ubuntu-server-template</name>
  <metadata>
    <libosinfo:libosinfo xmlns:libosinfo="http://libosinfo.org/xmlns/libvirt/domain/1.0">
      <libosinfo:os id="http://ubuntu.com/ubuntu/19.04"/>
    </libosinfo:libosinfo>
  </metadata>
  <memory unit='KiB'>2097152</memory>
  <currentMemory unit='KiB'>2097152</currentMemory>
  <vcpu placement='static'>1</vcpu>
  <os>
    <type arch='x86_64' machine='pc-q35-3.1'>hvm</type>
    <loader readonly='yes' type='pflash'>/usr/share/OVMF/OVMF_CODE.fd</loader>
    <nvram>/var/lib/libvirt/qemu/nvram/ubuntu-server-template_VARS.fd</nvram>
  </os>
  <features>
    <acpi/>
    <apic/>
    <vmport state='off'/>
  </features>
  <cpu mode='host-model' check='partial'>
    <model fallback='allow'/>
  </cpu>
  <clock offset='utc'>
    <timer name='rtc' tickpolicy='catchup'/>
    <timer name='pit' tickpolicy='delay'/>
    <timer name='hpet' present='no'/>
  </clock>
  <on_poweroff>destroy</on_poweroff>
  <on_reboot>restart</on_reboot>
  <on_crash>destroy</on_crash>
  <pm>
    <suspend-to-mem enabled='no'/>
    <suspend-to-disk enabled='no'/>
  </pm>
  <devices>
    <emulator>/usr/bin/qemu-system-x86_64</emulator>

    <disk type='file' device='disk'>
      <driver name='qemu' type='qcow2'/>
      <source file='/var/lib/libvirt/images/ubuntu-server-template.qcow2'/>
      <target dev='vda' bus='virtio'/>
      <boot order='2'/>
      <address type='pci' domain='0x0000' bus='0x03' slot='0x00' function='0x0'/>
    </disk>

    <disk type='file' device='cdrom'>
      <driver name='qemu' type='raw'/>
      <target dev='sda' bus='sata'/>
      <readonly/>
      <boot order='1'/>
      <address type='drive' controller='0' bus='0' target='0' unit='0'/>
    </disk>

    <controller type='usb' index='0' model='ich9-ehci1'>
      <address type='pci' domain='0x0000' bus='0x00' slot='0x1d' function='0x7'/>
    </controller>
    <controller type='usb' index='0' model='ich9-uhci1'>
      <master startport='0'/>
      <address type='pci' domain='0x0000' bus='0x00' slot='0x1d' function='0x0' multifunction='on'/>
    </controller>
    <controller type='usb' index='0' model='ich9-uhci2'>
      <master startport='2'/>
      <address type='pci' domain='0x0000' bus='0x00' slot='0x1d' function='0x1'/>
    </controller>
    <controller type='usb' index='0' model='ich9-uhci3'>
      <master startport='4'/>
      <address type='pci' domain='0x0000' bus='0x00' slot='0x1d' function='0x2'/>
    </controller>
    <controller type='sata' index='0'>
      <address type='pci' domain='0x0000' bus='0x00' slot='0x1f' function='0x2'/>
    </controller>
    <controller type='pci' index='0' model='pcie-root'/>
    <controller type='virtio-serial' index='0'>
      <address type='pci' domain='0x0000' bus='0x02' slot='0x00' function='0x0'/>
    </controller>
    <controller type='pci' index='1' model='pcie-root-port'>
      <model name='pcie-root-port'/>
      <target chassis='1' port='0x10'/>
      <address type='pci' domain='0x0000' bus='0x00' slot='0x02' function='0x0' multifunction='on'/>
    </controller>
    <controller type='pci' index='2' model='pcie-root-port'>
      <model name='pcie-root-port'/>
      <target chassis='2' port='0x11'/>
      <address type='pci' domain='0x0000' bus='0x00' slot='0x02' function='0x1'/>
    </controller>
    <controller type='pci' index='3' model='pcie-root-port'>
      <model name='pcie-root-port'/>
      <target chassis='3' port='0x12'/>
      <address type='pci' domain='0x0000' bus='0x00' slot='0x02' function='0x2'/>
    </controller>
    <controller type='pci' index='4' model='pcie-root-port'>
      <model name='pcie-root-port'/>
      <target chassis='4' port='0x13'/>
      <address type='pci' domain='0x0000' bus='0x00' slot='0x02' function='0x3'/>
    </controller>
    <controller type='pci' index='5' model='pcie-root-port'>
      <model name='pcie-root-port'/>
      <target chassis='5' port='0x14'/>
      <address type='pci' domain='0x0000' bus='0x00' slot='0x02' function='0x4'/>
    </controller>
    <controller type='pci' index='6' model='pcie-root-port'>
      <model name='pcie-root-port'/>
      <target chassis='6' port='0x15'/>
      <address type='pci' domain='0x0000' bus='0x00' slot='0x02' function='0x5'/>
    </controller>


    <interface type='network'>
      <mac address='52:54:00:6c:33:a5'/>
      <source network='default'/>
      <model type='virtio'/>
      <address type='pci' domain='0x0000' bus='0x01' slot='0x00' function='0x0'/>
    </interface>


    <serial type='pty'>
      <target type='isa-serial' port='0'>
        <model name='isa-serial'/>
      </target>
    </serial>
    <console type='pty'>
      <target type='serial' port='0'/>
    </console>
    <channel type='unix'>
      <target type='virtio' name='org.qemu.guest_agent.0'/>
      <address type='virtio-serial' controller='0' bus='0' port='1'/>
    </channel>
    <channel type='spicevmc'>
      <target type='virtio' name='com.redhat.spice.0'/>
      <address type='virtio-serial' controller='0' bus='0' port='2'/>
    </channel>
    <input type='tablet' bus='usb'>
      <address type='usb' bus='0' port='1'/>
    </input>
    <input type='mouse' bus='ps2'/>
    <input type='keyboard' bus='ps2'/>
    <graphics type='spice' autoport='yes'>
      <listen type='address'/>
      <image compression='off'/>
    </graphics>
    <sound model='ich9'>
      <address type='pci' domain='0x0000' bus='0x00' slot='0x1b' function='0x0'/>
    </sound>
    <video>
      <model type='virtio' heads='1' primary='yes'/>
      <address type='pci' domain='0x0000' bus='0x00' slot='0x01' function='0x0'/>
    </video>
    <redirdev bus='usb' type='spicevmc'>
      <address type='usb' bus='0' port='2'/>
    </redirdev>
    <redirdev bus='usb' type='spicevmc'>
      <address type='usb' bus='0' port='3'/>
    </redirdev>
    <memballoon model='virtio'>
      <address type='pci' domain='0x0000' bus='0x04' slot='0x00' function='0x0'/>
    </memballoon>
    <rng model='virtio'>
      <backend model='random'>/dev/urandom</backend>
      <address type='pci' domain='0x0000' bus='0x05' slot='0x00' function='0x0'/>
    </rng>
  </devices>
</domain>




     */
}
