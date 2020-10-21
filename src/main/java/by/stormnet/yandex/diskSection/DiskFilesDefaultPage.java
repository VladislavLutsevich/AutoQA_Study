package by.stormnet.yandex.diskSection;

import by.stormnet.yandex.AbstractPage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class DiskFilesDefaultPage extends AbstractPage {
    private static final Logger logger = LogManager.getLogger(DiskFilesDefaultPage.class);
    private static final By FILE_FOR_DELETING_LOCATOR = (By.xpath("//div//span[contains(text(),'textfile.txt')]"));
    private static final By TRASH_FOLDER_LOCATOR = (By.xpath("//div//span[contains(text(),'Корзина')]"));

    protected DiskFilesDefaultPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void moveFileTrashFolder() {
        Actions actions = new Actions(webDriver);
        actions.dragAndDrop(webDriver.findElement(FILE_FOR_DELETING_LOCATOR), webDriver.findElement(TRASH_FOLDER_LOCATOR)).perform();
    }

    public boolean isFileDeleted(){
        try {
            if(webDriver.findElement(FILE_FOR_DELETING_LOCATOR).isDisplayed()){
                logger.error("File was not deleted");
                return false;
            } else return true;
        } catch (TimeoutException e) {
            logger.error("File was not deleted");
        }
        return false;
    }
}
