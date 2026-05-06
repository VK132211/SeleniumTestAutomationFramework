package com.utility;

import com.constants.Browser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

public abstract class BrowserUtility {
    private WebDriver driver;

    public WebDriver getDriver() {
        return driver;
    }

    public BrowserUtility(WebDriver driver) {
        super();
        this.driver = driver;
    }

    public BrowserUtility(String browserName) {
        if(browserName.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
        }else if(browserName.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
        }else if(browserName.equalsIgnoreCase("safari")){
            driver = new SafariDriver();
        }else if(browserName.equalsIgnoreCase("edge")){
            driver = new EdgeDriver();
        }else{
            System.err.println("Invalid browser name");
        }

    }

    public BrowserUtility(Browser browserName) {
        if(browserName==Browser.CHROME){
            driver = new ChromeDriver();
        }else if(browserName==Browser.FIREFOX){
            driver = new FirefoxDriver();
        }else if(browserName==Browser.SAFARI){
            driver = new SafariDriver();
        }else if(browserName==Browser.EDGE){
            driver = new EdgeDriver();
        }else{
            System.err.println("Invalid browser name");
        }

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

    public String visibleText(By locator){
        WebElement webElement = driver.findElement(locator);
        return webElement.getText();
    }


}
