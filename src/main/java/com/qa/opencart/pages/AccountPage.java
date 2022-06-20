package com.qa.opencart.pages;

import com.qa.opencart.utility.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class AccountPage {
    private WebDriver driver;
    private ElementUtil ele;
    private By searchField = By.id("search_query_top");
    private By searchButton = By.name("submit_search");
    private By accountPageSection = By.xpath("//ul[@class = 'myaccount-link-list']/li");
    private By userAccountHeader = By.cssSelector(".header_user_info .account");
    private By logOut = By.cssSelector(".header_user_info .logout");
    private By homeScreen = By.cssSelector("#center_column h1");

    public AccountPage(WebDriver driver) {
        this.driver = driver;
        ele = new ElementUtil(driver);
    }

    public String getAccountPageTitle() {
        return ele.getPageTitle();
    }

    public String getAccountPageUrl() {
        return ele.getCurrentPageUrl();
    }

    public boolean isSearchExist() {
        return ele.getElement(searchField).isDisplayed();
    }

    public SearchPage doSearch(String productName) {
        if (isSearchExist()) {
            ele.doSendKeys(searchField, productName);
            ele.doClick(searchButton);
        }
        return new SearchPage(driver);
    }

    public List<String> getAccountPageSectionList() {

        List<WebElement> sectionList = ele.getElements(accountPageSection);
        List<String> sectionValues = new ArrayList<>();

        for (WebElement e : sectionList) {
            sectionValues.add(e.getText());
        }
        return sectionValues;
    }


    public void homeAccountPage() {
        if (isLinkExist(userAccountHeader)) {
            ele.doClick(userAccountHeader);
        }
    }

    public boolean isLinkExist(By locator){
        return ele.getElement(locator).isDisplayed();
    }

    public boolean doLogout(){
        if(isLinkExist(logOut)){
            ele.doClick(logOut);
            if(ele.getElement(homeScreen).getText().equals("AUTHENTICATION")){
                return true;
            }
        }
        return false;
    }
}
