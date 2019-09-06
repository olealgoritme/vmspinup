package model.hypervisors;

import java.net.URI;

public class HyperVisor {

     String name;
     String type;
     URI uri;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public URI getUri() {
        return uri;
    }

    public String getUriString() {
        return uri.toString();
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }
}
