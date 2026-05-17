package com.ui.pages;

import com.utility.BrowserUtility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Arrays;
import java.util.List;

public class SearchResultPage extends BrowserUtility {

    private static final By ALL_PRODUCT_LISTS_NAME=By.xpath("//h5[@itemprop=\"name\"]/a");
    private static final By PRODUCTS_LISTING_TITLE_LOCATOR=By.xpath("//span[@class=\"lighter\"]");
    public SearchResultPage(WebDriver driver) {
        super(driver);
    }
    
    public String getSearchResultsTitle(){
        return visibleText(PRODUCTS_LISTING_TITLE_LOCATOR);
    }
    
    public boolean isSearchTermPresent(String searchTerm){
        List<String> keywords = Arrays.asList(searchTerm.toLowerCase().split(" "));
        List<String> productNamesList = getAllVisibleText(ALL_PRODUCT_LISTS_NAME);
        boolean result = productNamesList.stream().anyMatch(name -> (keywords.stream().anyMatch(name.toLowerCase()::contains)));
        return result;
    }

    public ProductDetailPage clickOnTheProductAtIndex(int index){
        clickOn(getAllElements(ALL_PRODUCT_LISTS_NAME).get(index));

        ProductDetailPage productDetailsPage = new ProductDetailPage(getDriver());
        return productDetailsPage;

    }
}
