package com.ui.tests;

import com.ui.pages.HomePage;
import com.utility.BrowserUtility;
import com.utility.LoggerUtility;
import org.slf4j.Logger;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static com.constants.Browser.CHROME;

public class TestBase {
    protected HomePage homePage;
    Logger logger = LoggerUtility.getLogger(this.getClass());
    @BeforeMethod(description = "load the home page of the web app")
    public void setup() {
        logger.info("set up the home page of the web app");
        homePage = new HomePage(CHROME);

    }

    public BrowserUtility getInstance(){
        return homePage;
    }

    @AfterMethod(description = "tear down the browser")
    public void tearDown(){
        homePage.closeWindow();
    }

}
