package org.libvirt.event;

import org.libvirt.Domain;

/**
 * Interface for receiving events occurring on a domain.
 */
public interface LifecycleListener extends EventListener {

    /**
     * This method gets called when a change in the lifecycle
     * of a domain occurs.
     *
     * @param domain the domain on which the event occurred
     * @param event  contains information about the type of event
     *               that occurred and details about that event
     * @return the return value is currently ignored (was int, changed to void).
     */
     void onLifecycleChange(Domain domain,
                          DomainEvent event);
}
