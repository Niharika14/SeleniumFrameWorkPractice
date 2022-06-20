package com.qa.opencart.pages;

import com.qa.opencart.utility.ElementUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;
    private ElementUtil ele;
    private By userName = By.id("email");
    private By password = By.id("passwd");
    private By lgnButton = By.id("SubmitLogin");
    private By forgotLink = By.linkText("Forgot your password?");
    private By registerButton = By.id("SubmitCreate");
    private By registerEmail = By.id("email_create");

    public LoginPage(WebDriver driver){
        this.driver = driver;
        ele = new ElementUtil(driver);
    }

    public String getLoginPageTitle(){
        return ele.getPageTitle();
    }

    public String getLoginPageUrl(){
        return ele.getCurrentPageUrl();
    }

    public boolean isForgotLinkExist(){
        return ele.getElement(forgotLink).isDisplayed();
    }

    public boolean isRegistrationLinkExist(){
        return ele.getElement(registerButton).isDisplayed();
    }

    public AccountPage doLogin(String un, String pwd){
        ele.doSendKeys(userName,un);
        ele.doSendKeys(password,pwd);
        ele.doClick(lgnButton);
        return new AccountPage(driver);
    }

    public void navigateToRegisterPage(String email){
        if(isForgotLinkExist()){
            ele.doSendKeys(registerEmail,email);
            ele.doClick(registerButton);
        }
    }
}
