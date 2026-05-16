package com.ui.pages;

import com.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class MyAccountPage extends BrowserUtility {
    private static final By USER_NAME_LOCATOR=By.xpath("//a[@title='View my customer account']/span");
    private static final By SEARCH_BOX_LOCATOR=By.id("#search_query_top");
    private static final By SEARCH_BUTTON_LOCATOR=By.xpath("//button[@name=\"submit_search\"]");
    private static final By MY_FIRST_ADDRESS_LINK_LOCATOR=By.xpath("//a[@title='Add my first address']");
    public MyAccountPage(WebDriver driver) {
        super(driver);
    }

    public String getUserName(){
        return visibleText(USER_NAME_LOCATOR);
    }

    public SearchResultPage productSearch(String productName){
        enterText(SEARCH_BOX_LOCATOR,productName);
        clickOn(SEARCH_BUTTON_LOCATOR);
        SearchResultPage searchResultPage=new SearchResultPage(getDriver());
        return searchResultPage;
    }

    public AddressPage goToAddAddressPage(){
        clickOn(MY_FIRST_ADDRESS_LINK_LOCATOR);
        AddressPage addressPage = new AddressPage(getDriver());
        return addressPage;
    }
}
