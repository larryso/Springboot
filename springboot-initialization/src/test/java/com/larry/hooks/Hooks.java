package com.larry.hooks;

import com.larry.driver.SeleniumDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.util.TimeZone;
public class Hooks {
    @Before
    public void startup(){
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
    @After
    public void teardown(Scenario scenario){
        if(scenario.isFailed()){
            System.out.println("TEST "+ scenario.getName() + "FAiled!!!");
        }
        SeleniumDriver.quitDriver();
    }
}
