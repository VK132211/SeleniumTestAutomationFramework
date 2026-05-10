package com.ui.tests;

import static org.testng.Assert.*;

import com.ui.listeners.MyRetryAnalyzer;
import com.ui.pojo.User;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.ui.listeners.TestListener.class)
public class LoginTest extends TestBase {

    @Test(description = "Verifies with valid user is able to login to the application", groups = {"e2e", "sanity"}, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginTestDataProvider")
    public void Login(User user) {

        assertEquals(getHomePage().goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(), "Ffaga gdgdg");
    }

    @Test(description = "Verifies with valid user is able to login to the application", groups = {"e2e", "sanity"}, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginCSVTestDataProvider")
    public void LoginWithCSVData(User user) {

        assertEquals(getHomePage().goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(), "Ffaga gdgdg");
    }

    @Test(description = "Verifies with valid user is able to login to the application", groups = {"e2e", "sanity"}, dataProviderClass = com.ui.dataproviders.LoginDataProvider.class, dataProvider = "LoginExcelTestDataProvider", retryAnalyzer = MyRetryAnalyzer.class)
    public void LoginWithExcelData(User user) {
        assertEquals(getHomePage().goToLoginPage().doLoginWith(user.getEmailAddress(), user.getPassword()).getUserName(), "Ffaga gdgdg");
    }
}
