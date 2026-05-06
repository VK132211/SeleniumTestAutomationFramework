package com.ui.pages;

import com.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class LoginPage extends BrowserUtility {
    private static final By EMAIL_TEXT_BOX_LOCATOR = By.id("email");
    private static final By  PASSWORD_TEXT_BOX_LOCATOR = By.id("passwd");
    private static final By SUBMIT_LOGIN_LOCATOR = By.id("SubmitLogin");
    public LoginPage(WebDriver driver){
        super(driver);
    }

    public MyAccount doLoginWith(String email, String password){
        enterText(EMAIL_TEXT_BOX_LOCATOR, email);
        enterText(PASSWORD_TEXT_BOX_LOCATOR, password);
        clickOn(SUBMIT_LOGIN_LOCATOR);
        MyAccount myAccount = new MyAccount(getDriver());
        return  myAccount;

    }
}
