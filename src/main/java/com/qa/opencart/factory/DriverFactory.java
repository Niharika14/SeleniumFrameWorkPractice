package com.qa.opencart.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class DriverFactory {

    public static WebDriver driver;
    public Properties prop;

    public WebDriver init_driver(Properties prop) {

        String browserName = prop.getProperty("browserName").trim().toLowerCase();

        switch (browserName) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;

            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case "safari":
                WebDriverManager.safaridriver().setup();
                driver = new SafariDriver();
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                driver = new EdgeDriver();
                break;
            default:
                System.out.println("Please provide correct browser name.");
        }

        driver.manage().deleteAllCookies();
        driver.manage().window().fullscreen();
        driver.get(prop.getProperty("url"));
        return driver;
    }

    public Properties init_prop() {
        Properties prop = new Properties();
        FileInputStream ip = null;

        String evnName = System.getProperty("env");
        if (evnName == null) {
            try {
                ip = new FileInputStream("./src/test/resources/config/config.properties");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        } else
            try {
                switch (evnName.toLowerCase().trim()) {
                    case "qa":
                        ip = new FileInputStream("./src/test/resources/config/qa.config.properties");
                        break;
                    case "stage":
                        ip = new FileInputStream("./src/test/resources/config/stage.config.properties");
                        break;
                    case "dev":
                        ip = new FileInputStream("./src/test/resources/config/dev.config.properties");
                        break;
                    case "uat":
                        ip = new FileInputStream("./src/test/resources/config/uat.config.properties");
                        break;
                    default:
                        System.out.println("Please provide correct environment Name");
                }
            }catch (Exception e){

            }
        try {
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public static String getScreenshot() {
        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
        File destination = new File(path);
        try {
            FileUtils.copyFile(srcFile, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}
