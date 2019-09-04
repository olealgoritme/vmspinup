package model.vm;

import model.hypervisors.HyperVisor;

import java.util.UUID;

public class VirtualMachine {

    private int id;                // unique ID when instance is running
    private String name;           // name
    private String uuid;           // 128 bit long value (A UUID is made of up of hex digits  (4 chars each) along with 4 “-” symbols which make its length equal to 36 characters.)

    private int vCPU;              // vCPU count
    private int ram;               // delegated ram usage (GB)

    private int storage;           // storage in GB
    private String storage_path;   // path to storage image
    private String cdRom_path;     // path to CD-ROM
    private HyperVisor hyperVisor; // HyperVisor type


    public VirtualMachine() {
        // start off by creating a random 128 bit unsigned byte as UUID
        this.uuid = UUID.randomUUID().toString();
    }

    public String getStorage_path() {
        return storage_path;
    }

    public void setStorage_path(String storage_path) {
        this.storage_path = storage_path;
    }

    public int getStorage() {
        return storage;
    }

    public void setStorage(int storage) {
        this.storage = storage;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getvCPU() {
        return vCPU;
    }

    public void setvCPU(int vCPU) {
        this.vCPU = vCPU;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public HyperVisor getHyperVisor() {
        return hyperVisor;
    }

    public void setHyperVisor(HyperVisor hyperVisor) {
        this.hyperVisor = hyperVisor;
    }

    public String getCdRom_path() {
        return cdRom_path;
    }

    public void setCdRom_path(String cdRom_path) {
        this.cdRom_path = cdRom_path;
    }
}
