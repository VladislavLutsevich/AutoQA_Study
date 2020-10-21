package by.stormnet.yandex;

import org.openqa.selenium.WebDriver;

public abstract class AbstractPage {
    protected WebDriver webDriver;

    protected AbstractPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}
