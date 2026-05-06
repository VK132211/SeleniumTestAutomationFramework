package com.ui.tests;

import static com.constants.Browser.*;
import com.ui.pages.HomePage;

public class LoginTest {
    public static void main(String[] args) {
        HomePage homePage = new HomePage(CHROME);
        String userName = homePage.goToLoginPage().doLoginWith("dummy6thmay@test.com", "12345").getUserName();
        System.out.println(userName);

    }
}
