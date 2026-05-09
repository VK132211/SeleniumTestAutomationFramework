package com.ui.tests;

import static com.constants.Browser.*;

import com.ui.pages.HomePage;

import static org.testng.Assert.*;

import com.ui.pojo.User;
import com.utility.LoggerUtility;
import org.slf4j.Logger;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.ui.listeners.TestListener.class)
public class LoginTest {
    private HomePage homePage;
    Logger logger = LoggerUtility.getLogger(this.getClass());


    @BeforeMethod(description = "load the home page of the web app")
    public void setup() {
        logger.info("set up the home page of the web app");
        homePage = new HomePage(CHROME);

    }

    @Test(description = "Verifies with valid user is able to login to the application", groups = {"e2e", "sanity"}, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestDataProvider")
    public void Login(User user) {

        assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(), "Ffaga gdgdg");
    }

    @Test(description = "Verifies with valid user is able to login to the application", groups = {"e2e", "sanity"}, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginCSVTestDataProvider")
    public void LoginWithCSVData(User user) {

        assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(), "Ffaga gdgdg");
    }

    @Test(description = "Verifies with valid user is able to login to the application", groups = {"e2e", "sanity"}, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginExcelTestDataProvider", retryAnalyzer = com.ui.listeners.MyRetryAnalayzer.class)
    public void LoginWithExcelData(User user) {
        assertEquals(homePage.goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(), "Ffaga gdgdg");
    }
}
