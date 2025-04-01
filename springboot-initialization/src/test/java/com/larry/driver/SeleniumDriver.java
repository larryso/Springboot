package com.larry.driver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SeleniumDriver {
    private static RemoteWebDriver driver;

    private static final String CHROM_DRIVER_PATH = "/drivers/chromedriver.exe";

    private static ChromeDriver getChromeDriver(){
        String driverPath = SeleniumDriver.class.getResource(CHROM_DRIVER_PATH).getPath();
        System.setProperty("webdriver.chrome.driver", driverPath);
//        DesiredCapabilities capabilities = new DesiredCapabilities(BrowserType.CHROME, "", Platform.ANY);
        DesiredCapabilities capabilities = null;
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("--incognito");
        options.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
        return new ChromeDriver(options);
    }
    public static RemoteWebDriver getDriver(){
        if(driver == null)
            driver = getChromeDriver();
        return driver;
    }

    public static void quitDriver(){
        if(driver != null)
            driver.quit();
    }
}
