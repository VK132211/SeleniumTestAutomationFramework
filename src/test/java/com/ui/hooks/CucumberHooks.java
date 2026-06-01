package com.ui.hooks;

import com.constants.Browser;
import com.ui.cucumber.CucumberTestContext;
import com.ui.pages.HomePage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

import static com.utility.LambdaTestUtility.initializeLambdaTestSession;

public class CucumberHooks {

    @Before
    public void setup(Scenario scenario) {
        String browser = System.getProperty("browser", "chrome");
        boolean isHeadless = Boolean.parseBoolean(System.getProperty("isHeadless", "true"));
        boolean isLambdaTest = Boolean.parseBoolean(System.getProperty("isLambdaTest", "false"));

        if (isLambdaTest) {
            WebDriver driver = initializeLambdaTestSession(browser, scenario.getName());
            CucumberTestContext.setHomePage(new HomePage(driver));
            return;
        }

        CucumberTestContext.setHomePage(new HomePage(Browser.valueOf(browser.toUpperCase()), isHeadless));
    }

    @After
    public void tearDown() {
        HomePage homePage = CucumberTestContext.getHomePage();
        if (homePage != null) {
            homePage.closeWindow();
        }
        CucumberTestContext.clear();
    }
}
