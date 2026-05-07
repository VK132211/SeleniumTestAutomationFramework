package com.ui.tests;

import static com.constants.Browser.*;

import com.ui.pages.HomePage;

import static org.testng.Assert.*;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
    private HomePage homePage;

    @BeforeMethod(description = "load the home page of the web app")
    public void setup() {
        homePage = new HomePage(CHROME);

    }

    @Test(description = "Verifies with valid user is able to login to the application", groups = {"e2e", "sanity"})
    public void Login() {

        assertEquals(homePage.goToLoginPage().doLoginWith("dummy6thmay@test.com", "12345").getUserName(), "Ffaga gdgdg");
    }
}
