package com.ui.pages;

import com.constants.Browser;
import static com.constants.Env.*;
import com.utility.BrowserUtility;

import com.utility.JSONUtility;
import com.utility.LoggerUtility;
import org.openqa.selenium.By;
import org.slf4j.Logger;

public final class HomePage extends BrowserUtility {
    Logger logger = LoggerUtility.getLogger(this.getClass());

    private static final By SIGN_IN_LINK_LOCATOR = By.xpath("//a[contains(text(),\"Sign\")]");
    public HomePage(Browser browserName) {
        super(browserName);
        goToWebsite(JSONUtility.readJson(QA).getUrl());

    }
    public LoginPage goToLoginPage(){
        logger.info("trying to go to login page");
        clickOn(SIGN_IN_LINK_LOCATOR);
        LoginPage loginPage = new LoginPage(getDriver());
        return loginPage;
    }

}
