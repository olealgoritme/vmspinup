package com.lemon.vmspinup.builder;

import com.lemon.vmspinup.model.hypervisor.HyperVisor;
import com.lemon.vmspinup.xml.storage.Disk;
import com.lemon.vmspinup.xml.vm.*;

import static com.lemon.vmspinup.xml.vm.Features.*;

public final class DomainBuilder {

        private DomainBuilder() {
        }

        public static HyperVisorStep Builder() {
            return new DomainSteps();
        }

        public interface HyperVisorStep {
            NameStep withHyperVisor(HyperVisor.TYPE hyperVisor);
        }

        public interface NameStep {
            CPUStep withName(String name);
        }

        public interface CPUStep {
            MemoryStep withCPU(int vCPUs);
        }

        public interface MemoryStep {
            MemoryUnitsStep withMemory(long memory);
        }

        public interface MemoryUnitsStep {
            FeaturesStep withUnits(String memoryUnits);
        }

        public interface FeaturesStep {
            AddMoreFeaturesStep withFeature(TYPE feature);
            AddDiskStep withoutFeatures();
        }

        public interface AddMoreFeaturesStep extends AddDiskStep {
            AddMoreFeaturesStep andFeature(TYPE feature);
        }

        public interface AddDiskStep {
            AddMoreDisksStep withDisk(Disk disk);

        }

        public interface AddMoreDisksStep {
            AddMoreDisksStep andDisk(Disk disk);
            SerialConsoleStep withInterface(Interface.TYPE type);
        }

         public interface SerialConsoleStep {
            GraphicsStep withConsole(boolean wantConsole);
        }

        public interface GraphicsStep {
            DomainXML withGraphics(Graphics.TYPE type);
        }

    private static class DomainSteps implements HyperVisorStep, NameStep, CPUStep, MemoryStep, MemoryUnitsStep, FeaturesStep, AddMoreFeaturesStep, AddDiskStep, AddMoreDisksStep, SerialConsoleStep, GraphicsStep {

        private DomainXML domainXML;

        private DomainSteps() {
            domainXML = new DomainXML();
            // Standard for every domain
            domainXML.getOs()
                    .setType("hvm")
                    .setTypeArch("x86_64")
                    .setTypeMachine("q35");
        }

        @Override
        public NameStep withHyperVisor(HyperVisor.TYPE hyperVisor) {
            domainXML.setType(hyperVisor.name());
            return this;
        }

        @Override
        public CPUStep withName(String name) {
            domainXML.setName(name);
            return this;
        }

        @Override
        public MemoryStep withCPU(int vCPUs) {
            domainXML.setVcpu(vCPUs);
            return this;
        }

        @Override
        public MemoryUnitsStep withMemory(long memory) {
            domainXML.setMemory(memory);
            return this;
        }

        @Override
        public FeaturesStep withUnits(String memoryUnits) {
            domainXML.setMemoryUnit(memoryUnits);
            return this;
        }

        @Override
        public AddMoreFeaturesStep withFeature(TYPE feature) {
            addFeature(feature);
            return this;
        }

        @Override
        public AddMoreFeaturesStep andFeature(TYPE feature) {
            addFeature(feature);
            return this;
        }

        @Override
        public AddDiskStep withoutFeatures() {
            return this;
        }

        @Override
        public AddMoreDisksStep withDisk(Disk disk) {
            domainXML.addDisk(disk);
            return this;
        }

        @Override
        public AddMoreDisksStep andDisk(Disk disk) {
            domainXML.addDisk(disk);
            return this;
        }

        @Override
        public SerialConsoleStep withInterface(Interface.TYPE type) {

            Interface iFace = new Interface();
            iFace.setType(type);

                switch (type) {
                    case NETWORK:
                        iFace.setSourceNetwork("default");
                    break;

                    case BRIDGE:
                        iFace.setSourceBridge("virbr0");
                    break;
                }
            domainXML.addInterface(iFace);
            return this;
        }

        @Override
        public GraphicsStep withConsole(boolean wantConsole) {
            if(wantConsole) {
                SerialConsole console = new SerialConsole();
                console.setType("pty")
                        .setTargetPort("0");
                domainXML.addConsole(console);
            }
            return this;
        }

        @Override
        public DomainXML withGraphics(Graphics.TYPE type) {
            domainXML.addGraphics(new Graphics().setListen("0.0.0.0").setType(type).setPort("-1"));
            return domainXML;
        }

        private void addFeature(TYPE feature) {
            switch(feature) {
                case ACPI:
                    domainXML.getFeatures().enableAcpi();
                    break;
                case APIC:
                    domainXML.getFeatures().enableApic();
                    break;
                case PAE:
                    domainXML.getFeatures().enablePae();
                    break;
            }
        }

    }
}
