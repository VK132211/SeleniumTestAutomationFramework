package com.utility;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtility {

    private LoggerUtility() {
    }

    public static Logger getLogger(Class<?> clazz) {
        Logger logger = null;
        if (logger == null) {
            logger = LoggerFactory.getLogger(clazz);
        }
        return logger;
    }


}
