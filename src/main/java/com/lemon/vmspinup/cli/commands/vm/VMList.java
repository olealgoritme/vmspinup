package com.lemon.vmspinup.cli.commands.vm;

import com.jakewharton.fliptables.FlipTableConverters;
import com.lemon.vmspinup.app.ByteCalc;
import com.lemon.vmspinup.app.JAXBConvert;
import com.lemon.vmspinup.app.TimeCalc;
import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.cli.CliCommands;
import com.lemon.vmspinup.xml.vm.DomainXML;
import org.libvirt.Domain;
import org.libvirt.DomainInfo;
import org.libvirt.LibvirtException;
import picocli.CommandLine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

@CommandLine.Command(name = "list",
        aliases = {"ls"},
        description = "-- Lists VM Instances")
        /*header = {
                "@|green       _ _      _             |@",
                "@|green  __ _(_) |_ __| |_ __ _ _ _  |@",
                "@|green / _` | |  _(_-<  _/ _` | '_| |@",
                "@|green \\__, |_|\\__/__/\\__\\__,_|_|   |@",
                "@|green |___/                        |@"})
*/
public class VMList implements Runnable {

    private class VMPretty {
        private String ID;
        private String name;
        private String cpuTime;
        private String cpuCores;
        private String ram;
        private String virtualState;
        private String UUID;
        private String hyperVisor;
        private String VDI;

        public VMPretty() {
        }


        public String getCpuTime() {
            return cpuTime;
        }

        public void setCpuTime(String cpuTime) {
            this.cpuTime = cpuTime;
        }

        public String getVirtualState() {
            return virtualState;
        }

        public void setVirtualState(String virtualState) {
            this.virtualState = virtualState;
        }

        public String getCpuCores() {
            return cpuCores;
        }

        public void setCpuCores(String cpuCores) {
            this.cpuCores = cpuCores;
        }

        public String getRam() {
            return ram;
        }

        public void setRam(String ram) {
            this.ram = ram;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getUUID() {
            return UUID;
        }

        public void setUUID(String UUID) {
            this.UUID = UUID;
        }

        public String getHyperVisor() {
            return hyperVisor;
        }

        public void setHyperVisor(String hyperVisor) {
            this.hyperVisor = hyperVisor;
        }

        public String getVDI() {
            return VDI;
        }

        public void setVDI(String VDI) {
            this.VDI = VDI;
        }
    }

    @CommandLine.ParentCommand
    private CliCommands parent = null;

    @Override
    public void run() {

        VMSpinUp vmSpinUp = VMSpinUp.getInstance();
        ArrayList<Domain> vmList = vmSpinUp.vmList();
        ArrayList<VMPretty> prettyList = new ArrayList<>();
        VMPretty pretty;
        DomainInfo domainInfo;

        for(Domain domain : vmList) {

            try {
                pretty = new VMPretty();
                domainInfo = domain.getInfo();

                pretty.setName(domain.getName());
                pretty.setUUID(domain.getUUIDString());
                pretty.setID(Integer.toString(domain.getID()));
                pretty.setCpuTime(TimeCalc.getReadableTime(domainInfo.cpuTime));
                pretty.setCpuCores(Integer.toString(domain.getMaxVcpus()));
                pretty.setRam(ByteCalc.humanReadableByteCount(domainInfo.maxMem * 1024, true));
                pretty.setHyperVisor("KVM");

                // Trying to get graphics info (vnc/spice/port/ip)
                String xml = domain.getXMLDesc(0);
                DomainXML d = (DomainXML) new JAXBConvert(new Class[]{DomainXML.class}).xmlToObject(xml);
                
                if(d.getDevices().getGraphics().size() > 0) {
                    String port = d.getDevices().getGraphics().get(0).getPort();
                    String type = d.getDevices().getGraphics().get(0).getType();
                    pretty.setVDI(type + "://:" + port);
                    
                    // Trying to get IP address based on Mac address from hypervisor
                    // "arp -n | grep 52:54:00:46 | awk '{print $1}'"

                    if(d.getDevices().getInterfaces().size() > 0) {
                        String macAddr = d.getDevices().getInterfaces().get(0).getMacAddress();
                        ProcessBuilder builder = new ProcessBuilder();
                        Process process = null;
                        String cmd = "/usr/sbin/arp -n | grep " + macAddr +" | awk '{print $1}'";
                        builder.command("/bin/bash", "-c", cmd);
                        try {
                            process = builder.start();
                        } catch (IOException e) {
                            //
                        } finally {
                            if(process != null && process.getInputStream() != null) {
                                String line = null;
                                // TODO: check for correct IP syntax
                                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                                while((line = reader.readLine()) != null) {
                                    pretty.setVDI(type + "://" + line + ":" + port);
                                    break;
                                }
                            }
                        }
                    }
                }


                String color;
                switch (domainInfo.state) {
                    case VIR_DOMAIN_RUNNING:
                        color = "green";
                        pretty.setVirtualState("@|" + color + ",bold " + domainInfo.state.toString() + " |@");
                        break;
                    default:
                        color = "red";
                        pretty.setVirtualState("@|" + color + ",bold " + domainInfo.state.toString() + " |@  ");
                        break;
                }

                prettyList.add(pretty);

            } catch (LibvirtException | IOException e) {
                e.printStackTrace();
            }

        }

        System.out.println(CommandLine.Help.Ansi.ON.string(FlipTableConverters.fromIterable(prettyList, VMPretty.class)));

        System.out.println("\nTotal instances: " + prettyList.size() + "\n");
    }
}
