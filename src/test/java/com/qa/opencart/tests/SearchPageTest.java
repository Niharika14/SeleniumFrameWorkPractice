package com.qa.opencart.tests;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utility.Constants;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class SearchPageTest extends BaseTest {

    @BeforeClass
    public void accPageSetup() {
        accountPage = loginPage.doLogin(prop.getProperty("userName"), prop.getProperty("password"));
    }

    @Test
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
    public void searchResultValuesTest() {
        searchPage = accountPage.doSearch("dress");
        List<String> values = searchPage.getSearchResultValues();
        System.out.println("Searched items : " + values);
        Assert.assertEquals(values,Constants.SEARCH_PAGE_RESULT_VALUES);
    }
}
