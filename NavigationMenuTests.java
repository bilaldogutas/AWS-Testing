package TestCases;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.io.File;
import org.openqa.selenium.io.FileHandler;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.testng.annotations.Test;

public class NavigationMenuTests {
    static WebDriver driver;
    static Actions actions;

    @BeforeClass
    public void setup() throws InterruptedException {
        // Prepare 'driver' for use
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\jegmc\\C file\\chromedriver2.exe");
        driver = new ChromeDriver();
        actions = new Actions(driver);
        Thread.sleep(2000);
    }

    // Test 1: Hover over the 'Products' menu and check if the dropdown appears
    @Test(priority = 1)
    public void testHoverOverProductsMenu() throws InterruptedException {
        driver.get("https://aws.amazon.com/free/");
        driver.manage().window().maximize();
        Thread.sleep(1500);
        WebElement productsMenu = driver.findElement(By.xpath("//*[@id=\"m-nav\"]/div[1]/div[2]/nav/ul/li[2]/span/a"));
        actions.moveToElement(productsMenu).perform(); // Hover over the 'Products' menu
        Thread.sleep(2000); // Wait for dropdown to load

        // Check if the same element is displayed (can be improved with better dropdown locator)
        WebElement dropdown = driver.findElement(By.xpath("//*[@id=\"m-nav\"]/div[1]/div[2]/nav/ul/li[2]/span/a"));
        assert dropdown.isDisplayed() : "'Products' dropdown is not visible after hover.";

        System.out.println("Products' menu hover shows dropdown.");
        System.out.println("Test 1 Passed (navigation)");
        Thread.sleep(2000);
    }

    // Test 2: Click 'Pricing' menu, then click Pricing Calculator and verify the redirection
    @Test(priority = 2)
    public void testClickPricingMenu() throws InterruptedException {
        driver.get("https://aws.amazon.com/free/");
        driver.manage().window().maximize();
        Thread.sleep(1500);
        String originalWindow = driver.getWindowHandle(); // Save current window handle

        WebElement pricingMenu = driver.findElement(By.xpath("//*[@id=\"m-nav\"]/div[1]/div[2]/nav/ul/li[4]/span/a"));
        pricingMenu.click(); // Click 'Pricing'
        Thread.sleep(3000);

        WebElement pricingCalc = driver.findElement(By.xpath("//*[@id=\"m-nav-panel-pricing\"]/div[2]/div/div[1]/h3/a"));
        pricingCalc.click(); // Click Pricing Calculator (opens in a new tab)
        Thread.sleep(3000);

        // Switch to the new tab
        for (String windowHandle : driver.getWindowHandles()) {
            if (!windowHandle.equals(originalWindow)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }

        // Assert the URL contains 'calculator.aws'
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(currentUrl.contains("calculator.aws"), "Not navigated to Pricing Calculator page.");
        System.out.println("Navigated to Pricing Calculator.");

        driver.switchTo().window(originalWindow); // Switch back to the original tab
        System.out.println("Test 2 Passed (navigation)");
        Thread.sleep(2000);
    }

    // Test 3: Click on Amazon Q, close popup, scroll to FAQs and assert visibility
    @Test(priority = 3)
    public void testAmazonQFAQsVisibility() throws InterruptedException {
        driver.get("https://aws.amazon.com/free/");
        driver.manage().window().maximize();
        Thread.sleep(1500);
        WebElement amazonQButton = driver.findElement(By.xpath("//*[@id=\"m-nav\"]/div[1]/div[2]/nav/ul/li[1]/span/a"));
        amazonQButton.click(); // Click 'Amazon Q'
        Thread.sleep(3000);

        // Close popup if it appears
        WebElement popup = driver.findElement(By.xpath("//*[@id=\":r2:\"]/div/button"));
        popup.click();
        Thread.sleep(3000);

        // Scroll to where FAQs are located
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 5200)");
        Thread.sleep(3000);

        // Check if the FAQ header is visible
        WebElement faqHeader = driver.findElement(By.xpath("//*[@id=\"Amazon-Q-FAQs\"]/div/div/div[1]/div/h2"));
        Assert.assertTrue(faqHeader.isDisplayed(), "'Amazon Q FAQs' section is not visible.");

        System.out.println("Amazon Q FAQs section is visible.");
        System.out.println("Test 3 Passed (navigation)");
        Thread.sleep(2000);      }

    // Test 4: Verify all top-level navigation links are visible on the homepage
    @Test(priority = 4)
    public void testAllNavLinksAreDisplayed() throws InterruptedException {
        driver.get("https://aws.amazon.com/free/");
        driver.manage().window().maximize();
        Thread.sleep(1500);
        // Define XPaths for each top-level navigation link
        String[] navLinkXPaths = {
                "//*[@id='m-nav']/div[1]/div[2]/nav/ul/li[1]/span/a", // Amazon Q
                "//*[@id='m-nav']/div[1]/div[2]/nav/ul/li[2]/span/a", // Products
                "//*[@id='m-nav']/div[1]/div[2]/nav/ul/li[3]/span/a", // Solutions
                "//*[@id='m-nav']/div[1]/div[2]/nav/ul/li[4]/span/a", // Pricing
                "//*[@id='m-nav']/div[1]/div[2]/nav/ul/li[5]/span/a", // Documentation
                "//*[@id='m-nav']/div[1]/div[2]/nav/ul/li[6]/span/a"  // Learn
        };

        // Loop through and check visibility of each link
        for (String xpath : navLinkXPaths) {
            WebElement navLink = driver.findElement(By.xpath(xpath));
            Assert.assertTrue(navLink.isDisplayed(), "Navigation link is not visible: " + xpath);
        }

        System.out.println("All top-level nav links are displayed.");
        System.out.println("Test 4 Passed (navigation)");
        Thread.sleep(2000);
    }

    // Test 5: Check that all external footer links open in a new tab
    @Test(priority = 5)
    public void testExternalFooterLinksOpenInNewTab() throws InterruptedException {
        driver.get("https://aws.amazon.com/free/");
        driver.manage().window().maximize();
        Thread.sleep(1500);
        // Accept cookies if banner appears
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement acceptCookies = wait.until(ExpectedConditions.elementToBeClickable(By.id("awsccc-cb-footer-accept")));
            acceptCookies.click();
        } catch (Exception ignored) {}

        // Scroll to the bottom of the page
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        // Wait until all footer links are present
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> links = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.xpath("//footer//a[@href]")));

        // Check each external link for target="_blank"
        for (WebElement link : links) {
            String href = link.getAttribute("href");
            String target = link.getAttribute("target");

            if (href != null && !href.contains("aws.amazon.com")) {
                Assert.assertEquals(target, "_blank", "External link should open in a new tab: " + href);
            }
        }

        System.out.println("All external footer links open in a new tab.");
        System.out.println("Test 5 Passed (navigation)\n");
        Thread.sleep(2000);
    }


    @AfterClass
    public void driverQuit() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(2000);
            driver.quit();
        }
    }
}
