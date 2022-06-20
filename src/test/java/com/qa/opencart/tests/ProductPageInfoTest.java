package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utility.Constants;
import com.qa.opencart.utility.ExcelUtil;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.List;

public class ProductPageInfoTest extends BaseTest {

    @BeforeClass
    public void productInfoSetup() {
        accountPage = loginPage.doLogin(prop.getProperty("userName"), prop.getProperty("password"));
    }

    @DataProvider
    public Object[][] getData(Method m) {
        switch (m.getName()) {
            case "productImageTest":
                return new Object[][]{
                        {"dress", "Printed Dress", 2},
                        {"dress", "Printed Chiffon Dress", 4},
                        {"dress", "Faded Short Sleeve T-shirts", 4},
                        {"dress", "Blouse", 3}
                };

            case "productDiscountTest":
                return new Object[][]{
                        {"dress", "Printed Dress", false},
                        {"dress", "Printed Chiffon Dress", true},
                        {"dress", "Faded Short Sleeve T-shirts", false},
                        {"dress", "Blouse", false}
                };

            case "addToCartTest":
                return new Object[][]{
                        {"dress", "Printed Dress", "2", "M"},
                        {"dress", "Printed Chiffon Dress", "3", "L"},
                        {"dress", "Faded Short Sleeve T-shirts", "2", "S"},
                        {"dress", "Blouse", "4", "M"}
                };
        }
        return null;
    }


    @Test(dataProvider = "getData")
    public void productImageTest(String productName, String mainProduct, int imgCount) {
        searchPage = accountPage.doSearch(productName);
        productPageInfo = searchPage.selectProduct(mainProduct);
        int count = productPageInfo.getProductImageCount();
        System.out.println("Total Images are: " + count);
        Assert.assertEquals(count, imgCount);
    }

    @Test(dataProvider = "getData")
    public void productDiscountTest(String productName, String mainProduct, boolean flagDiscountResult) {
        searchPage = accountPage.doSearch(productName);
        productPageInfo = searchPage.selectProduct(mainProduct);
        boolean flag = productPageInfo.isDiscountAvailable();
        Assert.assertEquals(flag, flagDiscountResult);
    }

    @Test
    public void productPriceTest() {
        searchPage = accountPage.doSearch("dress");
        productPageInfo = searchPage.selectProduct("Printed Chiffon Dress");
        List<String> price = productPageInfo.getProductPrice();
        System.out.println(price);
        Assert.assertEquals(price, Constants.PRODUCT_PRICE);
    }

    @DataProvider
    public Object[][] getDiscountData(){
        Object productData[][] = ExcelUtil.getTestData("Sheet1");
        return productData;
    }

    @Test(dataProvider =  "getDiscountData" )
    public void discountCalculationTest(String productName, String mainProduct, String discount) {
        searchPage = accountPage.doSearch(productName);
        productPageInfo = searchPage.selectProduct(mainProduct);
        boolean flag = productPageInfo.verifyDiscountedPrice();
        Assert.assertSame(flag, Boolean.parseBoolean(discount));
    }

    @Test(dataProvider = "getData")
    public void addToCartTest(String productName, String mainProduct, String productQuantity, String size) throws InterruptedException {
        searchPage = accountPage.doSearch(productName);
        productPageInfo = searchPage.selectProduct(mainProduct);
        productPageInfo.selectQuantity(productQuantity);
        productPageInfo.selectSize(size);
        productPageInfo.doAddToCart();
        Thread.sleep(5000);
        productPageInfo.continueShopping();
    }

}
