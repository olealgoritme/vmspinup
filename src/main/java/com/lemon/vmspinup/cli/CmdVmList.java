package com.lemon.vmspinup.cli;

import com.jakewharton.fliptables.FlipTableConverters;
import com.lemon.vmspinup.app.ByteCalc;
import com.lemon.vmspinup.app.TimeCalc;
import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import org.libvirt.Domain;
import org.libvirt.DomainInfo;
import org.libvirt.LibvirtException;
import picocli.CommandLine;

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
public class CmdVmList implements Runnable {

    private class VMPretty {
        private String ID;
        private String name;
        private String cpuTime;
        private String cpuCores;
        private String ram;
        private String state;
        private String UUID;
        private String hyperVisor;

        public VMPretty() {
        }


        public String getCpuTime() {
            return cpuTime;
        }

        public void setCpuTime(String cpuTime) {
            this.cpuTime = cpuTime;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
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
                pretty.setState(domainInfo.state.toString());
                pretty.setHyperVisor("KVM");

                prettyList.add(pretty);

            } catch (LibvirtException e) {
                e.printStackTrace();
            }

        }

        parent.out.println(FlipTableConverters.fromIterable(prettyList, VMPretty.class));

        parent.out.println("\nTotal instances: " + prettyList.size() + "\n");
    }
}
