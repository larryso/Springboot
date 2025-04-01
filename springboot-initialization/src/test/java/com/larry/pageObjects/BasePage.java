package com.larry.pageObjects;

import com.larry.driver.SeleniumDriver;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    protected WebDriver webDriver;

    public BasePage(){
        webDriver = SeleniumDriver.getDriver();
    }

    public WebDriver getWebDriver(){
        return this.webDriver;
    }

}
