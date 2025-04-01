package com.larry.pageObjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class BaiduSearchPage extends BasePage{
    @FindBy(css = "[id='su']")
    private WebElement searchButton;

    public boolean searchButtonDisplayed(){
        return searchButton.isDisplayed();
    }
}
