package com.qa.opencart.pages;

import com.qa.opencart.utility.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SearchPage {

    private WebDriver driver;
    private ElementUtil ele;
    private By searchHeader = By.xpath("//*[@id='center_column']/h1/span[1]");
    private By searchItems = By.xpath("//div[@id='center_column']//ul/li//a[@class='product_img_link']");
    private By itemFoundText = By.xpath("//div[@id='center_column']//span[@class = 'heading-counter']");

    public SearchPage(WebDriver driver) {
        this.driver = driver;
        ele = new ElementUtil(driver);
    }

    public String getSearchHeader() {
        return ele.getElement(searchHeader).getText();
    }

    public String getSearchCountMessage() {
        return ele.getElement(itemFoundText).getText();
    }

    public int getSearchResultCount() {
        List<WebElement> itemCount = ele.getElements(searchItems);
        return itemCount.size();
    }

    public List<String> getSearchResultValues(){
        List<WebElement> itemCount = ele.getElements(searchItems);
        List<String> itemValues = new ArrayList<>();
        for(WebElement e: itemCount){
            itemValues.add(e.getAttribute("title"));
        }
        return itemValues;
    }

    public ProductPageInfo selectProduct(String mainProduct){
        List<WebElement> itemCount = ele.getElements(searchItems);
        for(WebElement e: itemCount){
            String text = e.getAttribute("title");
            if(text.equals(mainProduct)){
                e.click();
                break;
            }
        }
        return new ProductPageInfo(driver);
    }
}
