package com.ui.pages;

import com.constants.Browser;
import static com.constants.Env.*;
import com.utility.BrowserUtility;

import com.utility.JSONUtility;
import org.openqa.selenium.By;

public final class HomePage extends BrowserUtility {
    private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(),\"Sign\")]");
    public HomePage(Browser browserName) {
        super(browserName);
        goToWebsite(JSONUtility.readJson(QA).getUrl());

    }
    public LoginPage goToLoginPage(){
        clickOn(SIGN_IN_LINK_LOCATOR);
        LoginPage loginPage = new LoginPage(getDriver());
        return loginPage;
    }

}
