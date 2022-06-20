package com.qa.opencart.base;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductPageInfo;
import com.qa.opencart.pages.SearchPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.util.Properties;

public class BaseTest {

    public WebDriver driver;
    public Properties prop;
    public DriverFactory df;
    public LoginPage loginPage;
    public AccountPage accountPage;
    public SearchPage searchPage;
    public ProductPageInfo productPageInfo;

    @BeforeTest
    public void setup() {
        df = new DriverFactory();
        prop = df.init_prop();
        driver = df.init_driver(prop);
        loginPage = new LoginPage(driver);
    }

    @AfterTest
    public void tearDown() {
        driver.quit();
    }

}
