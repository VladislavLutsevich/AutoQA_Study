package by.stormnet.yandex.diskSection;

import by.stormnet.utils.WaitManager;
import by.stormnet.yandex.AbstractPage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class DiskLoadedFilesPage extends AbstractPage {
    private static final Logger logger = LogManager.getLogger(DiskLoadedFilesPage.class);
    private static final By LAST_ADDED_FILE_LOCATOR = (By.cssSelector("div.listing__items > div:nth-child(1)"));
    private static final By DISK_FILES_DEFAULT_FOLDER_LOCATOR = (By.cssSelector("div.navigation__scroll > div:nth-child(1) > div:nth-child(2) a"));
    private static final By MOVING_FILE_PROGRESSBAR_LOCATOR = (By.cssSelector("div.b-progressbar"));

    public DiskLoadedFilesPage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getFilesDefaultPageLink() {
        new WaitManager(webDriver).waitPageElement(DISK_FILES_DEFAULT_FOLDER_LOCATOR);
        return webDriver.findElement(DISK_FILES_DEFAULT_FOLDER_LOCATOR);
    }

    public void moveFileFromDownloadToMainFolder() {
        Actions actions = new Actions(webDriver);
        actions.dragAndDrop(webDriver.findElement(LAST_ADDED_FILE_LOCATOR), webDriver.findElement(DISK_FILES_DEFAULT_FOLDER_LOCATOR)).build().perform();
    }

    public DiskFilesDefaultPage goToDiskFilesDefaultPage() {
        try {
            new WaitManager(webDriver).waitInvisibleStatePageElement(MOVING_FILE_PROGRESSBAR_LOCATOR);
            logger.info("File was moved");
            getFilesDefaultPageLink().click();
        } catch (TimeoutException e) {
            logger.error("File was not moved");
        }
        return new DiskFilesDefaultPage(webDriver);
    }
}
