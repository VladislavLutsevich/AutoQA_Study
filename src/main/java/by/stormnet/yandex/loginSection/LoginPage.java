package by.stormnet.yandex.loginSection;

import by.stormnet.utils.WaitManager;
import by.stormnet.yandex.AbstractPage;
import by.stormnet.yandex.mailProfileSection.MainMailServicePage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends AbstractPage {
    private static final Logger logger = LogManager.getLogger(LoginPage.class);
    private static final By LOGIN_INPUT_FIELD_LOCATOR = (By.cssSelector(".Textinput-Control"));
    private static final By AUTH_FORM_LOGIN_BUTTON_LOCATOR = (By.cssSelector("[type|=submit]"));
    private static final By PASS_INPUT_FIELD_LOCATOR = (By.name("passwd"));
    private static final By AUTH_FORM_SUBMIT_BUTTON_LOCATOR = (By.cssSelector("[type|=submit]"));

    public LoginPage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getLoginInputField() {
        new WaitManager(webDriver).waitPageElement(LOGIN_INPUT_FIELD_LOCATOR);
        return webDriver.findElement(LOGIN_INPUT_FIELD_LOCATOR);
    }

    public WebElement getAuthFormLoginButton() {
        return webDriver.findElement(AUTH_FORM_LOGIN_BUTTON_LOCATOR);
    }

    public WebElement getPassInputField() {
        new WaitManager(webDriver).waitPageElement(PASS_INPUT_FIELD_LOCATOR);
        return webDriver.findElement(PASS_INPUT_FIELD_LOCATOR);
    }

    public WebElement getAuthFormSubmitButton() {
        return webDriver.findElement(AUTH_FORM_SUBMIT_BUTTON_LOCATOR);
    }

    public MainMailServicePage loginWithCreds(String username, String password) {
        getLoginInputField().sendKeys(username);
        getAuthFormSubmitButton().click();
        getPassInputField().sendKeys(password);
        getAuthFormSubmitButton().click();
        return new MainMailServicePage(webDriver);
    }
}

