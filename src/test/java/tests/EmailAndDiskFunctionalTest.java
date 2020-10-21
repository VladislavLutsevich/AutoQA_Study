package tests;

import by.stormnet.utils.TextFileCreator;
import by.stormnet.yandex.diskSection.DiskFilesDefaultPage;
import by.stormnet.yandex.diskSection.DiskLoadedFilesPage;
import by.stormnet.yandex.loginSection.LoginPage;
import by.stormnet.yandex.mailProfileSection.MailSendingForm;
import by.stormnet.yandex.mailProfileSection.MainMailServicePage;
import by.stormnet.yandex.mailProfileSection.YandexDiskIframe;
import by.stormnet.yandex.searchSection.SearchPage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

import static by.stormnet.utils.PropertiesManager.getProperty;

public class EmailAndDiskFunctionalTest extends AbstractTest {
    private static final Logger logger = LogManager.getLogger(EmailAndDiskFunctionalTest.class);

    @Test
    public void sendMailWithAttachedFile() {
        logger.trace("Start Test");
        SearchPage searchPage = new SearchPage(webDriver);
        LoginPage loginPage = searchPage.switchToLoginPage();
        logger.debug(getProperty("yandexCorrectLogin") + "/" +
                getProperty("yandexCorrectPass"));
        loginPage.loginWithCreds(
                getProperty("yandexCorrectLogin"),
                getProperty("yandexCorrectPass")
        );
        MainMailServicePage mainMailServicePage = new MainMailServicePage(webDriver);
        MailSendingForm mailSendingForm = mainMailServicePage.openMailSendingForm();
        logger.debug(getProperty("recipient"));
        mailSendingForm.setRecipient(getProperty("recipient"));
        TextFileCreator textFileCreator = new TextFileCreator();
        try {
            mailSendingForm.attachFile(textFileCreator.textFileCreate());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("Try to send email");
        mailSendingForm.sendMail();
        mainMailServicePage.refreshPage();
        mainMailServicePage.addToDiskFromLastReceivedEmail();
        YandexDiskIframe yandexDiskIframe = mainMailServicePage.switchToDiskIframe();
        DiskLoadedFilesPage diskLoadedFilesPage = yandexDiskIframe.goToDiskLoadedFilesPage();
        diskLoadedFilesPage.moveFileFromDownloadToMainFolder();
        DiskFilesDefaultPage diskFilesDefaultPage = diskLoadedFilesPage.goToDiskFilesDefaultPage();
        diskFilesDefaultPage.moveFileTrashFolder();
        logger.info("Start Check");
        Assert.assertTrue(diskFilesDefaultPage.isFileDeleted());
    }
}

