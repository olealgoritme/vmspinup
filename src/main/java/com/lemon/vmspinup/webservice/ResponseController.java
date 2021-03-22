package com.lemon.vmspinup.webservice;

import com.jakewharton.fliptables.FlipTableConverters;
import com.lemon.vmspinup.app.VMSpinUp;
import org.libvirt.Domain;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class ResponseController {

    private final AtomicLong responseIdCounter = new AtomicLong();

    private VMSpinUp vmSpinUp = VMSpinUp.getInstance();

    @RequestMapping("/")
    public Response root() {
        return new Response(this.createResponseID(),"VMSpinUp REST API v0.1");
    }

    @RequestMapping("/api")
    public Response api() {
        return new Response(this.createResponseID(),"VMSpinUp REST API v0.1");
    }

    @RequestMapping("/vmlist")
    public Response vmlist() {

            ArrayList<Domain> vmList = vmSpinUp.vmList();
            assert vmList != null;
            return new Response(this.createResponseID(), FlipTableConverters.fromIterable(vmList, Domain.class));
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
