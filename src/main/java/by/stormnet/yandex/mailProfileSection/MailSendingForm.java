package by.stormnet.yandex.mailProfileSection;

import by.stormnet.utils.WaitManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;

import static by.stormnet.utils.PropertiesManager.getProperty;


public class MailSendingForm {
    private WebDriver webDriver;
    private static final Logger logger = LogManager.getLogger(MailSendingForm.class);
    private static final By MAIL_TO_FIELD_LOCATOR = (By.cssSelector("div.MultipleAddressesDesktop.ComposeRecipients-MultipleAddressField.tst-field-to > div > div > div > div"));
    private static final By FILE_ATTACH_LOCATOR = (By.cssSelector(".ComposeAttachmentSourcesMenu-FileInput"));
    private static final By DONE_FILE_ATTACH_LOCATOR = (By.cssSelector(".ComposeAttachments-Item a[href]"));
    private static final By MAIL_SEND_BUTTON = (By.cssSelector("div.composeReact__footer > div > div:nth-child(1) div.ComposeControlPanelButton:nth-child(1)"));
    private static final By SUCCESS_MAIL_SENDING = (By.cssSelector(".ComposeDoneScreen-Title"));

    public MailSendingForm(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public WebElement getMailToField() {
        new WaitManager(webDriver).waitPageElement(MAIL_TO_FIELD_LOCATOR);
        return webDriver.findElement(MAIL_TO_FIELD_LOCATOR);
    }

    public WebElement getFileAttachInput() {
        return webDriver.findElement(FILE_ATTACH_LOCATOR);
    }

    public WebElement getMailSendingButton() {
        return webDriver.findElement(MAIL_SEND_BUTTON);
    }

//    public WebElement getSuccessMailSendingTitle() {
//        new WaitManager(webDriver).waitPageElement(SUCCESS_MAIL_SENDING);
//        return webDriver.findElement(SUCCESS_MAIL_SENDING);
//    }

    public void setRecipient(String recipient) {
        getMailToField().sendKeys(recipient);
    }

    public void attachFile(File file) throws InterruptedException {
        getFileAttachInput().sendKeys(file.getAbsolutePath());
        Thread.sleep(Integer.parseInt(getProperty("sleep_time")));
    }

    public void sendMail() {
        getMailSendingButton().click();
    }

    public boolean isMailSend() {
        try {
            new WaitManager(webDriver).waitPageElement(SUCCESS_MAIL_SENDING);
            logger.info("Mail was sent");
            return true;
        } catch (TimeoutException e) {
            logger.error("Mail was not sent");
            return false;
        }
    }
}
