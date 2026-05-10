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

public class TestBase {
    private final ThreadLocal<HomePage> homePage = new ThreadLocal<>();
    Logger logger = LoggerUtility.getLogger(this.getClass());

    @BeforeMethod(description = "load the home page of the web app")
    @Parameters({"browser","isLambdaTest","isHeadless"})
    public void setup(@Optional("chrome") String browser, @Optional("false") boolean isLambdaTest, @Optional("false") boolean isHeadless, ITestResult result) {
        WebDriver lambdaDriver;
        if(isLambdaTest){
            lambdaDriver = initializeLambdaTestSession(browser, result.getMethod().getMethodName());
            homePage.set(new HomePage(lambdaDriver));
        }else {
            logger.info("set up the home page of the web app");
            homePage.set(new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadless));
        }
    }

    protected HomePage getHomePage(){
        return homePage.get();
    }

    public BrowserUtility getInstance(){
        return getHomePage();
    }

    @AfterMethod(description = "tear down the browser")
    public void tearDown(){
        HomePage currentHomePage = getHomePage();
        if (currentHomePage != null) {
            currentHomePage.closeWindow();
            homePage.remove();
        }
    }

}
