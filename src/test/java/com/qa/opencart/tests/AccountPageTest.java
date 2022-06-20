package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utility.Constants;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class AccountPageTest extends BaseTest {

    @BeforeClass
    public void accPageSetup() {
        accountPage = loginPage.doLogin(prop.getProperty("userName"), prop.getProperty("password"));
    }

    @Test
    public void accountTitlePageTest() {
        String title = accountPage.getAccountPageTitle();
        System.out.println("Account Page Title is: " + title);
        Assert.assertEquals(title, Constants.ACCOUNT_PAGE_TITLE);
    }

    @Test
    public void accountSectionListTest() {
        accountPage.homeAccountPage();
        List<String> accountSectionValues = accountPage.getAccountPageSectionList();
        System.out.println("Account Page Section List is: " + accountSectionValues);
        Assert.assertEquals(accountSectionValues, Constants.ACCOUNT_PAGE_SECTION_VALUES);
    }

    @AfterClass
    public void logOutTest() {
        accountPage.homeAccountPage();
        boolean flag = accountPage.doLogout();
        Assert.assertEquals(flag, true);
    }

   /* @Test
    public void doSearchTest() {
        searchPage = accountPage.doSearch("dress");
        String headerValue = searchPage.getSearchHeader();
        Assert.assertEquals(headerValue, "\"DRESS\"");
    }

    @Test
    public void searchResultMessageTest() {
        searchPage = accountPage.doSearch("dress");
        String message = searchPage.getSearchCountMessage();
        System.out.println(message);
        Assert.assertEquals(message, Constants.SEARCH_PAGE_RESULT_MESSAGE);
    }


    @Test
    public void searchResultCountTest() {
        searchPage = accountPage.doSearch("dress");
        int count = searchPage.getSearchResultCount();
        System.out.println("Count of found item is: " + count);
        Assert.assertEquals(count, Constants.SEARCH_PAGE_RESULT_COUNT);
    }

    @Test
    public void searchResultValues() {
        searchPage = accountPage.doSearch("dress");
        List<String> values = searchPage.getSearchResultValues();
        System.out.println("Searched items : " + values);
        Assert.assertEquals(values,Constants.SEARCH_PAGE_RESULT_VALUES);
    }*/
}
