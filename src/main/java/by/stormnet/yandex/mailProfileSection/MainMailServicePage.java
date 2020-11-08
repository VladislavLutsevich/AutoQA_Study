package by.stormnet.yandex.mailProfileSection;

import by.stormnet.utils.WaitManager;
import by.stormnet.yandex.AbstractPage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;

public class MainMailServicePage extends AbstractPage {
    private static final Logger logger = LogManager.getLogger(MainMailServicePage.class);
    private static final By MAIL_SERVICE_PAGE_LOGO_LOCATOR = (By.cssSelector("div.yandex-header__logo"));
    private static final By MAIL_SENDING_FORM_LOCATOR = (By.cssSelector(".mail-ComposeButton-Wrap a"));
    private static By RECEIVED_FILE_ADD_TO_DISK_LOCATOR;
    private static final By DISK_IFRAME_LOCATOR = (By.cssSelector("div._nb-popup-content iframe"));
    private static final By REFRESH_ELEMENT_LOCATOR = (By.cssSelector("span.mail-ComposeButton-Refresh"));
    private static final By REFRESHING_BAR = (By.cssSelector("div.js-loading-bar"));

    public MainMailServicePage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getLogo() {
        new WaitManager(webDriver).waitPageElement(MAIL_SERVICE_PAGE_LOGO_LOCATOR);
        return webDriver.findElement(MAIL_SERVICE_PAGE_LOGO_LOCATOR);
    }

    public WebElement getMailSendingForm() {
        new WaitManager(webDriver).waitPageElement(MAIL_SENDING_FORM_LOCATOR);
        return webDriver.findElement(MAIL_SENDING_FORM_LOCATOR);
    }

    public WebElement getAddToDiskButton() {
        new WaitManager(webDriver).waitPageElement(RECEIVED_FILE_ADD_TO_DISK_LOCATOR);
        return webDriver.findElement(RECEIVED_FILE_ADD_TO_DISK_LOCATOR);
    }

    public WebElement getRefreshElement() {
        new WaitManager(webDriver).waitPageElement(REFRESH_ELEMENT_LOCATOR);
        return webDriver.findElement(REFRESH_ELEMENT_LOCATOR);
    }

    public MailSendingForm openMailSendingForm() {
        getMailSendingForm().click();
        return new MailSendingForm(webDriver);
    }

    public void addToDiskFromLastReceivedEmail() {
        getAddToDiskButton().click();
    }

    public YandexDiskIframe switchToDiskIframe() {
        new WaitManager(webDriver).waitIframeAndSwitch(DISK_IFRAME_LOCATOR);
        return new YandexDiskIframe(webDriver);
    }

    public boolean isOpened() {
        try {
            new WaitManager(webDriver).waitPageElement(MAIL_SERVICE_PAGE_LOGO_LOCATOR);
            logger.info("Page was opened");
            return true;
        } catch (TimeoutException e) {
            logger.error("Page was not opened");
            return false;
        }
    }

    public void refreshPage() {
        getRefreshElement().click();
        new WaitManager(webDriver).waitInvisibleStatePageElement(REFRESHING_BAR);
    }

    public void setFileLocator(File file){
        RECEIVED_FILE_ADD_TO_DISK_LOCATOR = (By.cssSelector("span[title~=\"" + file.getName() + "\"] > :nth-child(2)"));
    }
}
