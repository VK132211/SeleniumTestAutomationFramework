package com.ui.stepdefinitions;

import com.ui.cucumber.CucumberTestContext;
import com.ui.pages.LoginPage;
import com.ui.pages.MyAccountPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.testng.Assert.assertEquals;

public class LoginStepDefinitions {

    @Given("the user is on the login page")
    public void theUserIsOnTheLoginPage() {
        LoginPage loginPage = CucumberTestContext.getHomePage().goToLoginPage();
        CucumberTestContext.setLoginPage(loginPage);
    }

    @When("the user logs in with email {string} and password {string}")
    public void theUserLogsInWithEmailAndPassword(String emailAddress, String password) {
        MyAccountPage myAccountPage = CucumberTestContext.getLoginPage().doLoginWith(emailAddress, password);
        CucumberTestContext.setMyAccountPage(myAccountPage);
    }

    @When("the user tries to log in with email {string} and password {string}")
    public void theUserTriesToLogInWithEmailAndPassword(String emailAddress, String password) {
        LoginPage loginPage = CucumberTestContext.getLoginPage().doLoginWithInvalidCredentials(emailAddress, password);
        CucumberTestContext.setLoginPage(loginPage);
    }

    @Then("the account name should be {string}")
    public void theAccountNameShouldBe(String expectedAccountName) {
        assertEquals(CucumberTestContext.getMyAccountPage().getUserName(), expectedAccountName);
    }

    @Then("the login error message should be {string}")
    public void theLoginErrorMessageShouldBe(String expectedErrorMessage) {
        assertEquals(CucumberTestContext.getLoginPage().getErrorMessage(), expectedErrorMessage);
    }
}
