package com.lemon.vmspinup.xml.vm;
import javax.xml.bind.annotation.*;

import org.eclipse.persistence.oxm.annotations.XmlPath;

import java.io.Serializable;

@XmlRootElement(name = "interface")
@XmlAccessorType(XmlAccessType.FIELD)

@XmlType(propOrder = {"source", "ip", "route" })
public class Interface implements Serializable {

    public enum TYPE {
        NETWORK("network"),
        BRIDGE("bridge");

        TYPE(String type) {
            this.type = type;
        }
        private String type;
        public String getType() {
            return this.type;
        }
    }

    @XmlAttribute(name = "type")
    private TYPE type;

    @XmlPath("mac/@address")
    private String macAddress;

    @XmlAttribute(name = "source", required = true)
    private String source;

    @XmlPath("source/@bridge")
    private String sourceBridge;

    @XmlPath("source/@network")
    private String sourceNetwork;

    @XmlPath("source/@portgroup")
    private String sourceNetworkPortGroup;

    @XmlPath("target/@dev")
    private String targetDev;


    /**
     *     <ip address='192.168.122.5' prefix='24'/>
     *     <ip address='192.168.122.5' prefix='24' peer='10.0.0.10'/>
     */

    @XmlAttribute(name = "ip")
    private String ip;

    @XmlPath("ip/@address")
    private String ipAddress;

    @XmlPath("ip/@prefix")
    private String ipPrefix;

    @XmlPath("ip/@peer")
    private String ipPeer;


    /**
     *
     *     <route family='ipv4' address='192.168.122.0' prefix='24' gateway='192.168.122.1'/>
     *     <route family='ipv4' address='192.168.122.8' gateway='192.168.122.1'/>
     *
     */

    @XmlAttribute(name = "route")
    private String route;

    @XmlPath("route/@family")
    private String routeFamily;

    @XmlPath("route/@address")
    private String routeAddress;

    @XmlPath("route/@prefix")
    private String routePrefix;

    @XmlPath("route/@gateway")
    private String routeGateway;

    public String getType() {
        return this.type.name();
    }

    public Interface setType(TYPE type) {
        this.type = type;
        return this;
    }

    public String getMacAddress() {
        return this.macAddress;
    }

    public Interface setMacAddress(String macAddress) {
        this.macAddress = macAddress;
        return this;
    }
    

    public String getSourceBridge() {
        return this.sourceBridge;
    }

    public Interface setSourceBridge(String sourceBridge) {
        if(this.type.name().equalsIgnoreCase
                ("network") || this.sourceNetwork != null)
            throw new IllegalArgumentException("Can't set source.bridge when source.network already set");

        this.sourceBridge = sourceBridge;
        return this;
    }

    public String getTargetDev() {
        return this.targetDev;
    }

    public Interface setTargetDev(String targetDev) {
        this.targetDev = targetDev;
        return this;
    }

    public String getSourceNetworkPortGroup() {
        return this.sourceNetworkPortGroup;
    }

    public Interface setSourceNetworkPortGroup(String sourceNetworkPortGroup) {
        if(this.sourceNetwork == null)
            throw new IllegalArgumentException("Can't set source.network.portgroup when source.network isn't set");
        this.sourceNetworkPortGroup = sourceNetworkPortGroup;
        return this;
    }

    public String getSourceNetwork() {
        return this.sourceNetwork;
    }

    public Interface setSourceNetwork(String sourceNetwork) {
        if(this.type.name().equalsIgnoreCase
                ("bridge") || this.sourceBridge != null)
            throw new IllegalArgumentException("Can't set source.network when source.bridge already set");
        this.sourceNetwork = sourceNetwork;
        return this;
    }

    public String getRoutePrefix() {
        return this.routePrefix;
    }

    public Interface setRoutePrefix(String routePrefix) {
        this.routePrefix = routePrefix;
        return this;
    }

    public String getRouteAddress() {
        return this.routeAddress;
    }

    public Interface setRouteAddress(String routeAddress) {
        this.routeAddress = routeAddress;
        return this;
    }

    public String getRouteFamily() {
        return this.routeFamily;
    }

    public Interface setRouteFamily(String routeFamily) {
        this.routeFamily = routeFamily;
        return this;
    }

    public String getRouteGateway() {
        return this.routeGateway;
    }

    public Interface setRouteGateway(String routeGateway) {
        this.routeGateway = routeGateway;
        return this;
    }

    public String getIpPeer() {
        return this.ipPeer;
    }

    public Interface setIpPeer(String ipPeer) {
        this.ipPeer = ipPeer;
        return this;
    }

    public String getIpPrefix() {
        return this.ipPrefix;
    }

    public Interface setIpPrefix(String ipPrefix) {
        this.ipPrefix = ipPrefix;
        return this;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public Interface setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }
}
