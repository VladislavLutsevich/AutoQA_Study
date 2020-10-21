package tests;

import by.stormnet.utils.PropertiesManager;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class AbstractTest {
    protected WebDriver webDriver;

    @BeforeMethod
    public void preConditions(ITestContext context) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        webDriver = new ChromeDriver(chromeOptions);
        context.setAttribute("driver", webDriver);
        webDriver.get(PropertiesManager.getProperty("yandex_url"));
    }

    @AfterMethod
    public void postConditions() {
        webDriver.close();
        webDriver.quit();
    }
}
