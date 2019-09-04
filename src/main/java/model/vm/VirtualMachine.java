package model.vm;

import model.hypervisors.HyperVisor;
import org.libvirt.Domain;
import org.libvirt.StorageVol;

import java.util.UUID;

public class VirtualMachine {

    private int id;                         // unique ID when instance is running
    private String name;                    // name
    private String uuid;                    // 128 bit long value (A UUID is made of up of hex digits  (4 chars each) along with 4 “-” symbols which make its length equal to 36 characters.)

    private int vCPU;                       // vCPU count
    private int ramAmount;                  // delegated ram usage (GB)

    private int storageAmount;              // storage in GB
    private String storageVolumePath;       // storage volume image path
    private String isoPath;                 // path to ISO
    private HyperVisor hyperVisor;          // HyperVisor type


    public VirtualMachine() {


    }

    public VirtualMachine withUUID(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public VirtualMachine withStorageVolume(String storageVolumePath) {
        this.storageVolumePath = storageVolumePath;
        return this;
    }

    public VirtualMachine withHyperVisor(HyperVisor hyperVisor) {
        this.hyperVisor = hyperVisor;
        return this;
    }

    public void setStorage_path(StorageVol storage_path) {
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

    public VirtualMachine build() {
        return new VirtualMachine();
    }
}
