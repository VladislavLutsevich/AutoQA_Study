package by.stormnet.yandex.mailProfileSection;

import by.stormnet.utils.WaitManager;
import by.stormnet.yandex.diskSection.DiskLoadedFilesPage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Set;

public class YandexDiskIframe {
    private WebDriver webDriver;
    private static final Logger logger = LogManager.getLogger(YandexDiskIframe.class);
    private static final By DISK_IFRAME_LOADING_SPIN_LOCATOR = (By.cssSelector("div#app span.spin"));
    private static final By DOWNLOADS_FOLDER_LINK_LOCATOR = (By.cssSelector("div#app > div > div > span a"));

    public YandexDiskIframe(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public WebElement getDownloadsFolderLink() {
        new WaitManager(webDriver).waitPageElement(DOWNLOADS_FOLDER_LINK_LOCATOR);
        return webDriver.findElement(DOWNLOADS_FOLDER_LINK_LOCATOR);
    }

    public DiskLoadedFilesPage goToDiskLoadedFilesPage() {
        try {
            new WaitManager(webDriver).waitInvisibleStatePageElement(DISK_IFRAME_LOADING_SPIN_LOCATOR);
            logger.info("File was added");
            Set<String> currentWindows = webDriver.getWindowHandles();
            getDownloadsFolderLink().click();
            Set<String> newWindows = webDriver.getWindowHandles();
            newWindows.removeAll(currentWindows);
            String[] newWindow = newWindows.toArray(new String[0]);
            webDriver.switchTo().window(newWindow[0]);
        } catch (TimeoutException e) {
            logger.error("File was not added");
        }
        return new DiskLoadedFilesPage(webDriver);
    }
}
