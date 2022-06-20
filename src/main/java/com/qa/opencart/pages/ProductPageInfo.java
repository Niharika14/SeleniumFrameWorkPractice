package com.qa.opencart.pages;

import com.qa.opencart.utility.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class ProductPageInfo {
    private WebDriver driver;
    private ElementUtil ele;
    private By productHeader = By.xpath("//h1[@itemprop = 'name']");
    private By productRef = By.id("product_reference");
    private By productCondition = By.id("product_condition");
    private By productPriceInfo = By.xpath("//div[@class='price']//p");
    private By priceDiscount = By.id("reduction_percent");
    private By productImage = By.xpath("//div[@id='thumbs_list']//li");
    private By productQuantity = By.id("quantity_wanted");
    private By productSize = By.id("group_1");
    private By addToCartBtn = By.xpath("//span[text()='Add to cart']");
    private By continueShopping = By.xpath("//span[@title = 'Continue shopping']");

    public ProductPageInfo(WebDriver driver) {
        this.driver = driver;
        ele = new ElementUtil(driver);
    }


    public String getProductHeaderText() {
        return ele.getElement(productHeader).getText();
    }

    public String getProductCondition() {
        return ele.getElement(productCondition).getText();
    }

    public int getProductImageCount() {
        return ele.getElements(productImage).size();
    }

    public void selectQuantity(String quantity) {
        ele.doSendKeys(productQuantity, quantity);
    }

    public void selectSize(String size) {
        ele.doSelectByVisibleText(productSize, size);
    }

    public void doAddToCart() {
        ele.doClick(addToCartBtn);

    }

    public void continueShopping() {
        ele.doClick(continueShopping);
        }

    public List<String> getProductPrice() {
        List<WebElement> productPrice = ele.getElements(productPriceInfo);
        List<String> priceList = new ArrayList<>();
        for (WebElement e : productPrice) {
            String price = e.getText();
            if (!price.isEmpty()) {
                priceList.add(price);
            }
        }
        return priceList;
    }

    public boolean isDiscountAvailable() {
        String style = ele.getElement(priceDiscount).getAttribute("style");
        if (style.contains("inline-block")) {
            System.out.println("Discount is available");
            return true;

        } else {
            System.out.println("Discount is not available");
            return false;
        }
    }

    public boolean verifyDiscountedPrice() {
        if (isDiscountAvailable()) {
            List<String> productPrice = getProductPrice();
            float new_price = Float.parseFloat(productPrice.get(0).split("\\$")[1]);
            float discount = Float.parseFloat((productPrice.get(1).split("%")[0].split("-")[1]));
            float old_price = Float.parseFloat(productPrice.get(2).split("\\$")[1]);
            float discounted = ((old_price - new_price) / old_price) * 100;

            if (Math.round(discount)==Math.round(discounted)) {
                return true;
            }
        }
        return false;
    }
}