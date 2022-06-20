package com.qa.opencart.utility;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ElementUtil {
    public WebDriver driver;

    public ElementUtil(WebDriver driver){
        this.driver = driver;
    }
    public String getPageTitle(){
        return driver.getTitle();
    }

    public String getCurrentPageUrl(){
        return driver.getCurrentUrl();
    }

    public WebElement getElement(By locator){
        return driver.findElement(locator);
    }

    public List<WebElement> getElements(By locator){
        return driver.findElements(locator);
    }

    public void doSendKeys(By locator, String value){
        WebElement ele = getElement(locator);
        ele.clear();
        ele.sendKeys(value);
    }

    public void doClick(By locator){
        getElement(locator).click();
    }


    // ************************Drop Down Utils********************//
    public void doSelectByVisibleText(By locator, String text) {
        Select select = new Select(getElement(locator));
        select.selectByVisibleText(text);
    }

    public void doSelectByIndex(By locator, int index) {
        Select select = new Select(getElement(locator));
        select.selectByIndex(index);
    }

    public void doSelectByValue(By locator, String value) {
        Select select = new Select(getElement(locator));
        select.selectByValue(value);
    }

    public List<String> doGetDropDownOptions(By locator) {
        Select select = new Select(getElement(locator));
        List<WebElement> optionsList = select.getOptions();
        List<String> optionsValList = new ArrayList<String>();
        System.out.println(optionsList.size());

        for (WebElement e : optionsList) {
            String text = e.getText();
            optionsValList.add(text);
        }
        return optionsValList;
    }

    public void doSelectDropDownValue(By locator, String value) {
        Select select = new Select(getElement(locator));
        List<WebElement> optionsList = select.getOptions();
        for (WebElement e : optionsList) {
            String text = e.getText();
            if (text.equals(value)) {
                e.click();
                break;
            }
        }
    }

}
