package com.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BrowserUtility {
    private WebDriver driver;
    public BrowserUtility(WebDriver driver) {
        super();
        this.driver = driver;
    }

    public void goToWebsite(String url){
        driver.get(url);
    }
    public void maximizeWindow(){
        driver.manage().window().maximize();
    }
    public void clickOn(By locator){
        WebElement webElement = driver.findElement(locator);
        webElement.click();
    }
    public void enterText(By locator, String value){
        WebElement webElement = driver.findElement(locator);
        webElement.sendKeys(value);
    }


}
