package com.ui.tests;

import com.constants.Size;
import com.ui.pages.SearchResultPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductCheckOutTest extends TestBase {
    private static final String SEARCH_TERM = "Printed Summer Dress";
    private SearchResultPage searchResultPage;

    @BeforeMethod(description = "User logs into the application and searches for a product")
    public void setup() {
        searchResultPage = getHomePage().goToLoginPage().doLoginWith("dummy6thmay@test.com", "12345").productSearch(SEARCH_TERM);
    }


    @Test(description = "Verify if a logged in user able to buy product", groups = {"e2e", "sanity"})
    public void checkOutProduct() {
        String result = searchResultPage.clickOnTheProductAtIndex(0).changeSize(Size.L).addProductToCart().proceedToCheckout().goToConfirmAddressPage().goToShipmentPage().goToPaymentPage().makePayment();
        Assert.assertEquals(result, "No payment modules have been installed.");
    }
}
