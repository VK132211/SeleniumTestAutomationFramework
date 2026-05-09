package com.utility;

import com.constants.Browser;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BrowserUtility {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    Logger logger = LoggerUtility.getLogger(this.getClass());


    public WebDriver getDriver() {
        return driver.get();
    }

    public BrowserUtility(WebDriver driver) {
        super();
        BrowserUtility.driver.set(driver);
    }

    public BrowserUtility(String browserName) {
        driver.set(createDriver(browserName));
    }

    public BrowserUtility(Browser browserName,boolean headless) {
        driver.set(createDriver(browserName, headless));
    }

    private WebDriver createDriver(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            return new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            return new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("safari")) {
            return new SafariDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            return new EdgeDriver();
        }
        throw new IllegalArgumentException("Invalid browser name: " + browserName);
    }

    private WebDriver createDriver(Browser browserName, boolean headless) {
        if (browserName == Browser.CHROME ) {
            ChromeOptions options = new ChromeOptions();
            if (headless) {
                options.addArguments("--headless=old");
                options.addArguments("--window-size=1920,1080");
            }
            return new ChromeDriver(options);

        } else if (browserName == Browser.FIREFOX) {
            FirefoxOptions options = new FirefoxOptions();
            if (headless) {
            options.addArguments("--headless=old");
            options.addArguments("--window-size=1920,1080");
            }
            return new FirefoxDriver(options);
        } else if (browserName == Browser.SAFARI) {
            return new SafariDriver();
        } else if (browserName == Browser.EDGE) {
            EdgeOptions options = new EdgeOptions();
            if (headless) {
            options.addArguments("--headless=old");
            options.addArguments("--window-size=1920,1080");
            }
            return new EdgeDriver(options);
        }
        throw new IllegalArgumentException("Invalid browser name: " + browserName);
    }

    public void goToWebsite(String url) {
        logger.info("Going to URL: {}", url);
        getDriver().get(url);
    }

    public void maximizeWindow() {
        logger.info("Maximizing Window");
        getDriver().manage().window().maximize();
    }

    public void clickOn(By locator) {
        logger.info("Clicking on locator: {}", locator);
        WebElement webElement = getDriver().findElement(locator);
        webElement.click();
    }

    public void enterText(By locator, String value) {
        logger.info("Entering text: {}", value);
        WebElement webElement = getDriver().findElement(locator);
        webElement.sendKeys(value);
    }

    public String visibleText(By locator) {
        logger.info("Visible text: {}", locator);
        WebElement webElement = getDriver().findElement(locator);
        return webElement.getText();
    }

    public String takeScreenshot(String name) {
        logger.info("Taking screenshot: {}", name);
        TakesScreenshot screenshot = (TakesScreenshot) getDriver();
        String screenshotName = name + "--" + formatDate(new Date()) + ".png";
        String path = System.getProperty("user.dir") + "//screenshots//" + screenshotName;
        try {
            File screenshotData = screenshot.getScreenshotAs(OutputType.FILE);
            File screenshotFile = new File(path);
            FileUtils.forceMkdirParent(screenshotFile);
            FileUtils.copyFile(screenshotData, screenshotFile);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return path;
    }

    public void closeWindow() {
        logger.info("Closing Window");
        getDriver().quit();
    }

    public String formatDate(Date date) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        return simpleDateFormat.format(date);
    }


}
