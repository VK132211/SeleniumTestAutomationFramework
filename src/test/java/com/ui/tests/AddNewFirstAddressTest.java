package com.ui.tests;

import com.ui.pages.MyAccountPage;
import com.ui.pojo.Address;
import com.utility.FakeAddressUtility;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AddNewFirstAddressTest extends TestBase{
    private Address address;
    private MyAccountPage myAccountPage;
    @BeforeMethod(description = "valid first time user logins to the application")
    public void setup(){
        myAccountPage=getHomePage().goToLoginPage().doLoginWith("dummy6thmay@test.com","12345");
        address = FakeAddressUtility.getFakeAddress();
    }

    @Test(description = "add new address")
    public void addNewAddress(){
        String newAddress=myAccountPage.goToAddAddressPage().saveAddress(address);
        Assert.assertEquals(newAddress,address.getAddressAlias().toUpperCase());
    }

}
