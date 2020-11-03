package com.ciic.reporter.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestLog {
    static Logger logger = LoggerFactory.getLogger(TestLog.class);
    public static void main(String[] arge) {
        logger.debug(" debug");
        logger.info(" info");
        logger.error(" error");
        logger.warn(" warn");
    }
}
