package com.ui.tests;

import com.ui.pages.HomePage;
import com.ui.pages.MyAccountPage;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(com.ui.listeners.TestListener.class)
public class SearchProductTest extends TestBase {
    private MyAccountPage myAccountPage;
    private static final String SEARCH_TERM="Mens Wallet";
    public void setup() {
        myAccountPage=getHomePage().goToLoginPage().doLoginWith("dummy6thmay@test.com", "12345");
    }

    @Test(description = "Verify If the logged in customer is able to search for a product and correct products are returned", groups = {"e2e", "sanity"})
    public void verifySearchProduct() {
        myAccountPage.productSearch(SEARCH_TERM);
    }
}
