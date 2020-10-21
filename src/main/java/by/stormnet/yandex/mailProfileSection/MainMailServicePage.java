package by.stormnet.yandex.mailProfileSection;

import by.stormnet.utils.WaitManager;
import by.stormnet.yandex.AbstractPage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class MainMailServicePage extends AbstractPage {
    private static final Logger logger = LogManager.getLogger(MainMailServicePage.class);
    private static final By MAIL_SERVICE_PAGE_LOGO_LOCATOR = (By.cssSelector("div.yandex-header__logo"));
    private static final By MAIL_SENDING_FORM_LOCATOR = (By.cssSelector(".mail-ComposeButton-Wrap a"));
    private static final By EMAIL_LISTING_LOCATOR = (By.cssSelector(".ns-view-messages"));
    private static final By RECEIVED_FILE_ADD_TO_DISK_LOCATOR = (By.cssSelector("span[title~=\"textfile.txt\"] > :nth-child(2)"));
    private static final By DISK_IFRAME_LOCATOR = (By.cssSelector("div._nb-popup-content iframe"));

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
        webDriver.navigate().refresh();
        new WaitManager(webDriver).waitPageElement(EMAIL_LISTING_LOCATOR);
    }
}
