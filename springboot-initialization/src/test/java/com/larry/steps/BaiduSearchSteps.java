package com.larry.steps;

import com.larry.pageObjects.BaiduSearchPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class BaiduSearchSteps {
    private  BaiduSearchPage baiduSearchPage;

    @Given("^I have open the browser$")
    public void openBrowser(){
        baiduSearchPage = new BaiduSearchPage();
    }
    @When("^When I open Baidu website$")
    public void openSearchPage(){
        baiduSearchPage.getWebDriver().navigate().to("www.baidu.com");
    }
    @Then("^Then search button should exist$")
    public void checkSearchButton(){
        boolean buttonDisplayed = baiduSearchPage.searchButtonDisplayed();
        System.out.println("TEST --------> "+ buttonDisplayed);
    }
}
