package com.lemon.vmspinup.cli.commands.vm;

import com.lemon.vmspinup.app.VMSpinUp;
import org.libvirt.Domain;
import org.libvirt.LibvirtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.UUID;

public interface VMAllCommands {

    Logger LOG = LoggerFactory.getLogger(VMSpinUp.class);
    VMSpinUp vmSpinUp = VMSpinUp.getInstance();



    default Domain vmCreateAndStart(String domainXML) {
        Domain domain;
        try {

            // TODO: schema validation
            //  enum virDomainDefineFlags {
            //  VIR_DOMAIN_DEFINE_VALIDATE	=	1 (0x1; 1 << 0)
            //  Validate the XML document against schema
            //  }
            domain = vmSpinUp.connect.domainDefineXML(domainXML);
            domain.create();
            domain.setAutostart(true);
        } catch (LibvirtException e) {
            LOG.error("Could not create domain!");
            return null;
        }
            return domain;
    }

    default Domain vmDefineByXML(String domainXML) {
       Domain domain;
        try {
            domain = vmSpinUp.connect.domainDefineXML(domainXML);
        } catch (LibvirtException e) {
            LOG.error("Could not define domain!");
            return null;
        }
        return domain;
    }

    default ArrayList<Domain> vmList() {
        int[] ids;
        ArrayList<Domain> domainList = new ArrayList<>();
        Domain domain;
        try {

            ids = vmSpinUp.connect.listDomains();
            for (int i : ids) {
                domain = this.vmLookupByID((i));
                domainList.add(domain);
            }

        } catch (LibvirtException e) {
            LOG.error("Could not get domain list!");
            return null;
        }
        return domainList;
    }

    default Domain vmLookupByUUID(UUID uuid) {
        Domain domain;
        try {
            domain = vmSpinUp.connect.domainLookupByUUID(uuid);
        } catch (LibvirtException e) {
            LOG.error("Could not lookup domain!");
            return null;
        }
        return domain;
    }

    default Domain vmLookupByID(int id) {
        Domain domain;
        try {
            domain = vmSpinUp.connect.domainLookupByID(id);
        } catch (LibvirtException e) {
            LOG.error("Could not lookup domain!");
            return null;
        }
        return domain;
    }
    default Domain vmLookupByName(String name) {
        Domain domain;
        try {
            domain = vmSpinUp.connect.domainLookupByName(name);
        } catch (LibvirtException e) {
            LOG.error("Could not lookup domain!");
            return null;
        }
        return domain;
    }

}
