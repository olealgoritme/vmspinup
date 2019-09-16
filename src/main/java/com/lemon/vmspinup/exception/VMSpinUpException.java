package com.lemon.vmspinup.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class VMSpinUpException extends Throwable {

    public VMSpinUpException(Class className, String exception) {
        Logger LOG = LoggerFactory.getLogger(className);
        LOG.error(exception);
    }

}
