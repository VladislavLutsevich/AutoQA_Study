package by.stormnet.yandex.searchSection;

import by.stormnet.yandex.AbstractPage;
import by.stormnet.yandex.loginSection.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Set;

public class SearchPage extends AbstractPage {
    private static final By MAIN_PAGE_LOGIN_BUTTON_LOCATOR = (By.xpath("//div[@class='desk-notif__wrapper']//a[@role='button']"));

    public SearchPage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getMainPageLoginButton() {
        return webDriver.findElement(MAIN_PAGE_LOGIN_BUTTON_LOCATOR);
    }

    public LoginPage switchToLoginPage() {
        Set<String> currentWindows = webDriver.getWindowHandles();
        SearchPage searchPage = new SearchPage(webDriver);
        searchPage.getMainPageLoginButton().click();
        Set<String> newWindows = webDriver.getWindowHandles();
        newWindows.removeAll(currentWindows);
        String[] newWindow = newWindows.toArray(new String[0]);
        webDriver.switchTo().window(newWindow[0]);
        return new LoginPage(webDriver);
    }
}
