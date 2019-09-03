package model.hypervisors;

import java.net.URI;

public abstract class AbstractHyperVisor {

     String name;
     String type;
     URI uri;

    @Override
    public String toString() {
        return null;
    }

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

    public void setUri(URI uri) {
        this.uri = uri;
    }
}
