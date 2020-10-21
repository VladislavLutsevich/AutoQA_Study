package by.stormnet.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static by.stormnet.utils.PropertiesManager.getProperty;

public class WaitManager {
    protected WebDriver webDriver;

    public WaitManager(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void waitPageElement(By elementLocator) {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Integer.parseInt(getProperty("wait_time")));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(elementLocator));
    }

    public void waitInvisibleStatePageElement(By elementLocator) {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Integer.parseInt(getProperty("wait_time")));
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(elementLocator));
    }

    public void waitIframeAndSwitch(By elementLocator) {
        WebDriverWait webDriverWait = new WebDriverWait(webDriver, Integer.parseInt(getProperty("wait_time")));
        webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(elementLocator));
    }
}
