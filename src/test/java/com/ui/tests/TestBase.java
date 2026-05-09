package com.ui.tests;

import com.constants.Browser;
import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LoggerUtility;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import static com.utility.LambdaTestUtility.*;

import static com.constants.Browser.CHROME;

public class TestBase {
    protected HomePage homePage;
    Logger logger = LoggerUtility.getLogger(this.getClass());

    @BeforeMethod(description = "load the home page of the web app")
    @Parameters({"browser","isLambdaTest","isHeadless"})
    public void setup(@Optional("chrome") String browser, @Optional("false") boolean isLambdaTest, @Optional("false") boolean isHeadless, ITestResult result) {
        WebDriver lambdaDriver;
        if(isLambdaTest){
            lambdaDriver = initializeLambdaTestSession("chrome", result.getMethod().getMethodName());
            homePage=new HomePage(lambdaDriver);
        }else {
            logger.info("set up the home page of the web app");
            homePage = new HomePage(Browser.valueOf("chrome".toUpperCase()), isHeadless);
        }
    }

    public BrowserUtility getInstance(){
        return homePage;
    }

    @AfterMethod(description = "tear down the browser")
    public void tearDown(){
        homePage.closeWindow();
    }

}
