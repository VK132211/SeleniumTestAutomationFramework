package com.ui.listeners;

import com.aventstack.extentreports.Status;

import static  com.utility.ExtentReportUtility.*;

import com.ui.tests.TestBase;
import com.utility.BrowserUtility;
import com.utility.LoggerUtility;
import org.slf4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.Arrays;

public class TestListener implements ITestListener{
    Logger logger = LoggerUtility.getLogger(this.getClass());


    public void onTestStart(ITestResult result) {
        logger.info("{} started", result.getMethod().getDescription());
        logger.info(result.getMethod().getMethodName());
        logger.info(Arrays.toString(result.getMethod().getGroups()));
        String testName = result.getTestName();
        if (testName == null || testName.isBlank()) {
            testName = result.getMethod().getMethodName();
        }
        createExtentTest(testName);
    }


    public void onTestSuccess(ITestResult result) {
        // not implemented
        logger.info("{} passed", result.getMethod().getMethodName());
        if (getTest() != null) {
            getTest().log(Status.PASS, result.getMethod().getMethodName()+"Passed");
            removeTest();
        }
    }


    public void onTestFailure(ITestResult result) {
        logger.error("{} failed", result.getMethod().getMethodName());
        if (getTest() != null) {
            getTest().log(Status.FAIL, result.getMethod().getMethodName()+"Failed");
        }
        Object testclass = result.getInstance();
        BrowserUtility browserUtility = ((TestBase) testclass).getInstance();
        if (browserUtility == null || browserUtility.getDriver() == null) {
            logger.warn("Skipping screenshot because browser was not initialized");
            removeTest();
            return;
        }
        logger.info("Capturing the screenshot for the failed test");
        String screenshotPath = browserUtility.takeScreenshot(result.getMethod().getMethodName());
        logger.info("Attaching the screenshot for the failed test to the report");
        if (getTest() != null) {
            getTest().addScreenCaptureFromPath(screenshotPath);
            removeTest();
        }
    }


   public void onTestSkipped(ITestResult result) {
       logger.warn("{} skipped", result.getMethod().getDescription());
       if (getTest() != null) {
           getTest().log(Status.SKIP, result.getMethod().getMethodName()+"Skipped");
           removeTest();
       }
    }

    public void onStart(ITestContext context) {
        logger.info("Test suite started");
        setUpSparkReporter("report.html");
    }


    public void onFinish(ITestContext context) {
        logger.info("Test suite finished");
        flushReport();
    }
}
