package com.ui.listeners;

import com.utility.LoggerUtility;
import org.slf4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class TestListener implements ITestListener{
    Logger logger = LoggerUtility.getLogger(this.getClass());

    public void onTestStart(ITestResult result) {
        logger.info(result.getMethod().getDescription() + " started");
        logger.info(result.getMethod().getMethodName());
        logger.info(Arrays.toString(result.getMethod().getGroups()));
    }


    public void onTestSuccess(ITestResult result) {
        // not implemented
        logger.info(result.getMethod().getMethodName() + " passed");
    }


    public void onTestFailure(ITestResult result) {
        logger.error(result.getMethod().getMethodName() + " failed");
    }


   public void onTestSkipped(ITestResult result) {
       logger.warn(result.getMethod().getDescription() + " skipped");
    }

    public void onStart(ITestContext context) {
        logger.info("Test suite started");
    }


    public void onFinish(ITestContext context) {
        logger.info("Test suite finished");    }
}
