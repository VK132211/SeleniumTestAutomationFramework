package com.ui.cucumber;

import com.ui.pages.HomePage;
import com.ui.pages.LoginPage;
import com.ui.pages.MyAccountPage;

public final class CucumberTestContext {
    private static final ThreadLocal<HomePage> HOME_PAGE = new ThreadLocal<>();
    private static final ThreadLocal<LoginPage> LOGIN_PAGE = new ThreadLocal<>();
    private static final ThreadLocal<MyAccountPage> MY_ACCOUNT_PAGE = new ThreadLocal<>();

    private CucumberTestContext() {
    }

    public static HomePage getHomePage() {
        return HOME_PAGE.get();
    }

    public static void setHomePage(HomePage homePage) {
        HOME_PAGE.set(homePage);
    }

    public static LoginPage getLoginPage() {
        return LOGIN_PAGE.get();
    }

    public static void setLoginPage(LoginPage loginPage) {
        LOGIN_PAGE.set(loginPage);
    }

    public static MyAccountPage getMyAccountPage() {
        return MY_ACCOUNT_PAGE.get();
    }

    public static void setMyAccountPage(MyAccountPage myAccountPage) {
        MY_ACCOUNT_PAGE.set(myAccountPage);
    }

    public static void clear() {
        HOME_PAGE.remove();
        LOGIN_PAGE.remove();
        MY_ACCOUNT_PAGE.remove();
    }
}
