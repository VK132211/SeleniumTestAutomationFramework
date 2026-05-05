package com.ui.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LoginTest {
    public static void main(String[] args) {
        WebDriver wd = new ChromeDriver();
        wd.get("https://automationpractice.techwithjatin.com/");
        wd.manage().window().maximize();
        
        By signInLinkLocator = By.xpath("//a[contains(text(),\"Sign\")]");
        WebElement signInLinkWebElement = wd.findElement(signInLinkLocator);
        signInLinkWebElement.click();

        By emailTextBoxLocator = By.id("email");
        WebElement emailTextBoxWebElement = wd.findElement(emailTextBoxLocator);
        emailTextBoxWebElement.sendKeys("dummy5thMay@test.com");

        By passwordTextBoxLocator = By.id("passwd");
        WebElement passwordTextBoxElement = wd.findElement(passwordTextBoxLocator);
        passwordTextBoxElement.sendKeys("dummy5thMay");

        By submitLoginButtonLocator = By.id("SubmitLogin");
        WebElement submitLoginButtonWebElement = wd.findElement(submitLoginButtonLocator);
        submitLoginButtonWebElement.click();


    }
}
