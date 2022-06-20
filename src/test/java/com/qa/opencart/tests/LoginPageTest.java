package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utility.Constants;
import io.netty.util.Constant;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginPageTest extends BaseTest {

    @Test(priority = 1)
    public void loginPageTest(){
        String title = loginPage.getLoginPageTitle();
        System.out.println("Login Page Title is: "+ title);
        Assert.assertEquals(title, Constants.LOGIN_PAGE_TITLE);
    }

    @Test(priority = 2)
    public void loginPageUrlTest(){
        String url = loginPage.getLoginPageUrl();
        System.out.println("Login Page Url is: "+ url);
        Assert.assertEquals(url,Constants.LOGIN_PAGE_URL);
    }


    @Test(priority = 3)
    public void forgottenLinkExistTest(){
        boolean flag = loginPage.isForgotLinkExist();
        Assert.assertTrue(flag);
    }

    @Test(priority = 4)
    public void doLoginTest(){
        loginPage.doLogin(prop.getProperty("userName"), prop.getProperty("password"));
    }

}
