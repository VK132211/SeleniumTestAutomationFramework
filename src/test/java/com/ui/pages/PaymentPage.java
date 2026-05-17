package com.ui.pages;

import com.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PaymentPage extends BrowserUtility {
    private static final By PAYMENT_MODULES_NOT_FOUND_ALERT_LOCATOR= By.className("alert");
    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    public String makePayment(){
        return visibleText(PAYMENT_MODULES_NOT_FOUND_ALERT_LOCATOR);
    }
}
