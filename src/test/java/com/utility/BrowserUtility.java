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
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public abstract class BrowserUtility {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    Logger logger = LoggerUtility.getLogger(this.getClass());
    private WebDriverWait wait;

    public WebDriver getDriver() {
        return driver.get();
    }

    public BrowserUtility(WebDriver driver) {
        super();
        BrowserUtility.driver.set(driver);
        wait=new WebDriverWait(driver, Duration.ofSeconds(30L));

    }

    public BrowserUtility(String browserName) {
        driver.set(createDriver(browserName));
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30L));
    }

    public BrowserUtility(Browser browserName,boolean headless) {
        driver.set(createDriver(browserName, headless));
        wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30L));
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
                options.addArguments("--headless=new");
                options.addArguments("--window-size=1920,1080");
            }
            return new ChromeDriver(options);

        } else if (browserName == Browser.FIREFOX) {
            FirefoxOptions options = new FirefoxOptions();
            if (headless) {
            options.addArguments("--headless");
            options.addArguments("--window-size=1920,1080");
            }
            return new FirefoxDriver(options);
        } else if (browserName == Browser.SAFARI) {
            return new SafariDriver();
        } else if (browserName == Browser.EDGE) {
            EdgeOptions options = new EdgeOptions();
            if (headless) {
            options.addArguments("--headless=new");
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

    public void clearText(By locator) {
        logger.info("Clearing text: {}", locator);
        WebElement webElement= getDriver().findElement(locator);
        webElement.clear();
    }

    public void selectFromDropDown(By locator, String value) {
        logger.info("Selecting drop down locator: {}", locator);
        WebElement webElement = wait.until(driver -> {
            WebElement element = driver.findElement(locator);
            Select select = new Select(element);
            boolean optionExists = select.getOptions().stream()
                    .anyMatch(option -> option.getText().trim().equals(value));
            return optionExists ? element : null;
        });
        Select select = new Select(webElement);
        logger.info("Selecting drop down value: {}", value);
        if (webElement.isDisplayed()) {
            select.selectByVisibleText(value);
            return;
        }

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) getDriver();
        javascriptExecutor.executeScript(
                """
                const select = arguments[0];
                const text = arguments[1];
                const option = [...select.options].find(item => item.text.trim() === text);
                if (!option) {
                    throw new Error(`Option not found: ${text}`);
                }
                select.value = option.value;
                select.dispatchEvent(new Event('change', { bubbles: true }));
                """,
                webElement,
                value
        );
    }

    public String takeScreenshot(String name) {
        logger.info("Taking screenshot: {}", name);
        TakesScreenshot screenshot = (TakesScreenshot) getDriver();
        String screenshotName = name + "--" + formatDate(new Date()) + ".png";
        String path = "./screenshots/" + screenshotName;
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
