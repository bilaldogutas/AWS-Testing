package TestCases;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.io.File;
import org.openqa.selenium.io.FileHandler;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.testng.annotations.Test;

public class DropdownAndFilterTests {
    static WebDriver driver;

    @BeforeClass
    public void setup() throws InterruptedException {
        // Prepare 'driver' for use
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\jegmc\\C file\\chromedriver2.exe");
        driver = new ChromeDriver();
        Thread.sleep(2000);
    }

    @Test(priority = 1)
    void testFreeTierCategoriesDropdown() throws InterruptedException {
        driver.get("https://aws.amazon.com/free/");
        driver.manage().window().maximize();
        Thread.sleep(1500);
        // Click category filter
        WebElement categoryDropdown = driver.findElement(By.xpath("//*[@id=\"popover-category-trigger\"]"));
        categoryDropdown.click();
        Thread.sleep(2000);

        // verify options are there
        List<WebElement> categories = driver.findElements(By.xpath("//*[@id=\"popover-category-trigger\"]"));
        assert categories.size() > 0 : "No category options found.";
        System.out.println("Test 1 Passed (dropdown)");
    }

    @Test(priority = 2)
    void testFeaturedOffersForBusinesses() throws InterruptedException {
        driver.get("https://aws.amazon.com/free/");
        driver.manage().window().maximize();
        Thread.sleep(1500);
        // store the current window handle
        String originalWindow = driver.getWindowHandle();

        // click dropdown
        WebElement offersDropdown = driver.findElement(By.xpath("//*[@id=\"popover-offers-trigger\"]"));
        offersDropdown.click();
        Thread.sleep(2000);

        // click startup in dropdown
        WebElement startups = driver.findElement(By.xpath("//*[@id=\"offers\"]/a[2]"));
        startups.click();
        Thread.sleep(3000);

        // wait for the new tab to go back to the original
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        // verify on the new tab
        String pageSource = driver.getPageSource().toLowerCase();
        assert pageSource.contains("startup") : "Expected 'startup' content not found in new tab.";

        System.out.println("Test 2 Passed (dropdown)");

        // close the new tab and switch back to the original
        driver.close();
        driver.switchTo().window(originalWindow);
        Thread.sleep(2000);
    }

    @Test(priority = 3)
    void testMarketPlaceDropdown() throws InterruptedException {
        driver.get("https://aws.amazon.com/free/");
        driver.manage().window().maximize();
        Thread.sleep(1500);
        // select marketplace dropdown
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"m-nav\"]/div[1]/div[2]/nav/ul/li[8]/span/a"));
        dropdown.click();
        Thread.sleep(2000);

        // close dropdown
        WebElement closeDropdown = driver.findElement(By.xpath("//*[@id=\"m-nav\"]/div[2]/i[1]"));
        closeDropdown.click();
        Thread.sleep(1000);

        // verify dropdown
        String pageSource = driver.getPageSource().toLowerCase();
        assert pageSource.contains("browse aws marketplace") : "Expected marketplace, not found after filter.";

        System.out.println("Test 3 Passed (dropdown)");
    }

    @Test(priority = 4)
    void testFeaturedFilter() throws InterruptedException {
        driver.get("https://aws.amazon.com/free/");
        driver.manage().window().maximize();
        Thread.sleep(1500);
        // scroll down to option
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 1250)");
        Thread.sleep(1000);

        // select feature filter
        WebElement twelveMonthsFree = driver.findElement(By.xpath("//*[@id=\"awsf-all-free-tier-tier-12monthsfree\"]"));
        twelveMonthsFree.click();
        Thread.sleep(2000);

        // select application integration feature
        WebElement applicationIntegrationFilter = driver.findElement(By.xpath("//*[@id=\"awsf-all-free-tier-categories-app-integration\"]"));
        applicationIntegrationFilter.click();
        Thread.sleep(3000);

        // verify
        String pageSource = driver.getPageSource().toLowerCase();
        assert pageSource.contains("mq") : "Expected filter services not found.";
        System.out.println("Test 4 Passed (dropdown)");
    }

    @Test(priority = 5)
    void testLanguageDropdown() throws InterruptedException {
        driver.get("https://aws.amazon.com/free/");
        driver.manage().window().maximize();
        Thread.sleep(1500);
        // click language dropdown
        WebElement languageDropdown = driver.findElement(By.xpath("//*[@id=\"m-nav-language-selector\"]"));
        languageDropdown.click();
        Thread.sleep(3000);

        // select spanish
        WebElement spanish = driver.findElement(By.xpath("//*[@id=\"popover-language-selector\"]/div[1]/div[1]/ul/li[4]/a"));
        spanish.click();
        Thread.sleep(2000);

        // verify language changes
        String pageSource = driver.getPageSource().toLowerCase();
        assert pageSource.contains("nivel gratuito de aws") : "language not displayed properly.";

        System.out.println("Test 5 Passed (dropdown)\n");
    }

    @AfterClass
    public void driverQuit() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(2000);
            driver.quit();
        }
    }
}
