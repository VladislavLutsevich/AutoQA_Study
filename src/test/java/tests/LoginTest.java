package tests;

import by.stormnet.yandex.loginSection.LoginPage;
import by.stormnet.yandex.mailProfileSection.MainMailServicePage;
import by.stormnet.yandex.searchSection.SearchPage;
import io.qameta.allure.testng.AllureTestNg;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.TestListener;

import static by.stormnet.utils.PropertiesManager.*;

@Listeners({AllureTestNg.class, TestListener.class})
public class LoginTest extends AbstractTest {
    private static final Logger logger = LogManager.getLogger(AbstractTest.class);

    @Test
    public void positiveLoginTest() {
        logger.trace("Start Test");
        SearchPage searchPage = new SearchPage(webDriver);
        LoginPage loginPage = searchPage.switchToLoginPage();
        logger.debug(getProperty("yandexCorrectLogin") + "/" +
                getProperty("yandexCorrectPass"));
        MainMailServicePage mainMailServicePage = loginPage.loginWithCreds(
                getProperty("yandexCorrectLogin"),
                getProperty("yandexCorrectPass")
        );
        logger.info("Start Check");
        Assert.assertTrue(mainMailServicePage.isOpened());
    }

    @Test
    public void negativeLoginTest() {
        logger.trace("Start Test");
        SearchPage searchPage = new SearchPage(webDriver);
        LoginPage loginPage = searchPage.switchToLoginPage();
        logger.warn("Try to login with wrong password");
        logger.debug(getProperty("yandexCorrectLogin") + "/" +
                getProperty("yandexIncorrectPass"));
        MainMailServicePage mainMailServicePage = loginPage.loginWithCreds(
                getProperty("yandexCorrectLogin"),
                getProperty("yandexIncorrectPass")
        );
        logger.info("Start Check");
        Assert.assertFalse(mainMailServicePage.isOpened());
    }
}

