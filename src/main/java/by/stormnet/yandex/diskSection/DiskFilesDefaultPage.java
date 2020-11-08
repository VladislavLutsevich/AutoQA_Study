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

import java.io.File;

public class DiskFilesDefaultPage extends AbstractPage {
    private static final Logger logger = LogManager.getLogger(DiskFilesDefaultPage.class);
    private static By FILE_FOR_DELETING_LOCATOR;
    private static final By TRASH_FOLDER_LOCATOR = (By.xpath("//span[contains(@class, 'file-icon_dir_trash') or contains(@class, 'file-icon_dir_trash-full')]/../../parent::div[contains(@class, \"js-prevent-deselect\")]"));
    private static final By DELETING_FILE_PROGRESSBAR_LOCATOR = (By.cssSelector("div.b-progressbar__fill"));

    protected DiskFilesDefaultPage(WebDriver webDriver) {
        super(webDriver);
    }

    public WebElement getFileToDelete() {
        new WaitManager(webDriver).waitPageElement(FILE_FOR_DELETING_LOCATOR);
        return webDriver.findElement(FILE_FOR_DELETING_LOCATOR);
    }

    public WebElement getTrashFolder() {
        new WaitManager(webDriver).waitPageElement(TRASH_FOLDER_LOCATOR);
        return webDriver.findElement(TRASH_FOLDER_LOCATOR);
    }

    public void moveFileTrashFolder() {
        Actions actions = new Actions(webDriver);
        actions.dragAndDrop(getFileToDelete(), getTrashFolder()).build().perform();
    }

    public boolean isFileDeleted() {
        try {
            new WaitManager(webDriver).waitInvisibleStatePageElement(DELETING_FILE_PROGRESSBAR_LOCATOR);
            logger.error("File was deleted");
            return true;
        } catch (TimeoutException e) {
            logger.error("File was not deleted");
        }
        return false;
    }

    public void setFileLocator(File file){
        FILE_FOR_DELETING_LOCATOR = (By.xpath("//span[contains(text(), '" + file.getName() + "')]/../../parent::div[contains(@class, \"js-prevent-deselect\")]"));
    }
}
