package com.lemon.vmspinup.webservice;

import com.lemon.vmspinup.app.VMSpinUp;
import com.lemon.vmspinup.model.vm.VirtualMachine;
import org.libvirt.DomainInfo;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ResponseController {

    private final AtomicLong responseIdCounter = new AtomicLong();

    private VirtualMachine vm = null;
    private VMSpinUp vmSpinUp = VMSpinUp.getInstance();
    private DomainInfo df = null;
    private StringBuilder stringBuilder = new StringBuilder();

    @RequestMapping("/client")
    public String client() {
        return "Welcome to VMSpinUp REST API v0.1";
    }

    @RequestMapping("/vmlist")
    public Response vmlist() {

            ArrayList<VirtualMachine> vmList = vmSpinUp.listVMs();
            assert vmList != null;
            stringBuilder.append(String.format("%15s %3s %3s %3s %3s %3s %3s %3s %3s %3s %3s", "VM Instance", "|", "ID", "|", "OS", "|", "RAM", "|", "vCPUs", "|", "STATE"));
            stringBuilder.append(String.format("%s", "-----------------------------------------------------------------------"));


            for(VirtualMachine vm : vmList) {
                stringBuilder.append(String.format("%15s %3s %3s %3s %3s %3s %3s %2.5s %3s %3s", vm.getName(), "|", vm.getID(), "|", "OS=HVM", "|", (vm.getRamAmount() / 1024 / 1024) + " GB", "|", vm.getvCPU(), "--state--"));
            }
            return new Response(this.createResponseID(), stringBuilder.toString());
    }

    @RequestMapping("/{vmName}/{info}")
    public Response greeting(@PathVariable(value="vmName") String vmName,
                             @PathVariable(value="info") String info) {
            return new Response(this.createResponseID(), "not ready yet");
    }

    private long createResponseID() {
        return responseIdCounter.incrementAndGet();
    }
}
