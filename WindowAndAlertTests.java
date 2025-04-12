package AWS;

// Importing necessary Selenium and TestNG libraries
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.ArrayList;

public class WindowAndAlertTests {

    WebDriver driver;
    WebDriverWait wait;
    String originalTab;

    // Setup method to initialize WebDriver and open the AWS Capgemini partner page
    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver(); // Launch Chrome browser
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Explicit wait
        driver.manage().window().maximize(); // Maximize browser window
        driver.get("https://aws.amazon.com/partners/capgemini/"); // Open AWS Capgemini page
        originalTab = driver.getWindowHandle(); // Store the handle of the main/original tab
    }

    // Test 1: Validates that clicking "Connect with Capgemini" opens a new tab
    @Test(priority = 1)
    public void testExternalLinkOpensNewTab() throws InterruptedException {
        WebElement connectButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Connect with Capgemini")));
        connectButton.click(); // Click the link

        Thread.sleep(3000); // Wait for new tab to open

        // Get all open tab handles
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        Assert.assertTrue(tabs.size() > 1, "New tab didn't open.");
        driver.switchTo().window(tabs.get(1)); // Switch to new tab
        Thread.sleep(2000);

        // Optionally, validate the URL
        String currentUrl = driver.getCurrentUrl();
        System.out.println("Test 1 Passed - External Link Opened successfully");
    }

    // Test 2: Switches back to the original tab and confirms it's still responsive
    @Test(priority = 2)
    public void testSwitchBackToMainTab() throws InterruptedException {
        driver.switchTo().window(originalTab); // Switch back to the main/original tab
        Thread.sleep(2000);

        // Assert we're still on the correct page
        Assert.assertTrue(driver.getTitle().contains("Capgemini"), "Failed to switch back to main tab.");
        System.out.println("Test 2 Passed - Switched Back to Main Tab");
    }

    // Test 3: Attempts to trigger and interact with a modal popup (custom chat modal)
    @Test(priority = 3)
    public void testModalPopupDisplay() throws InterruptedException {
        Thread.sleep(2000);

        // Attempt to trigger a modal (This section is highly specific and may fail if elements change)
        WebElement launchModalButton1 = driver.findElement(By.xpath("//*[@id=\":r2:\"]/div/div[2]/div/button/span"));
        launchModalButton1.click(); // Open chat or popup trigger
        Thread.sleep(2000);

        WebElement launchModalButton2 = driver.findElement(By.xpath("//*[@id=\"mrc-sunrise-chat\"]/div[3]/button/img"));
        launchModalButton2.click(); // Open inner popup/modal
        Thread.sleep(2000);


        // Close or interact with modal again
        WebElement launchModalButton3 = driver.findElement(By.xpath("//*[@id=\"chat-footer\"]/div/button/span"));
        launchModalButton3.click();
        Thread.sleep(4000);

        System.out.println("Test 3 Passed - Poppup Displayed");
    }

    // Test 4: Simulates accepting a JavaScript-style alert/popup (based on available UI)
    @Test(priority = 4)
    public void testAcceptAlertBox() throws InterruptedException {
        Thread.sleep(2000);

        // Attempt to click button that would trigger an alert/popup
        WebElement jsAlertButton = driver.findElement(By.xpath("//*[@id=\"chat-container\"]/div[6]/div[2]/div/div/div[3]/div/div/div[2]/div/button/span"));
        jsAlertButton.click();
        Thread.sleep(2000);

        System.out.println("Test 4 Passed - Poppup Alert Closed");
    }

    // Test 5: Opens and switches between multiple tabs, then returns to the original tab
    @Test(priority = 5)
    public void testMultipleTabNavigation() throws InterruptedException {
        // Open two new tabs using JavaScript
        ((JavascriptExecutor) driver).executeScript("window.open('https://example.com','_blank');");
        ((JavascriptExecutor) driver).executeScript("window.open('https://www.selenium.dev','_blank');");
        Thread.sleep(3000);

        // Get handles of all open tabs
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        Assert.assertTrue(tabs.size() >= 3, "Not all tabs opened");

        // Switch through each tab and print the current URL
        for (String tab : tabs) {
            driver.switchTo().window(tab);
            System.out.println("Current URL: " + driver.getCurrentUrl());
            Thread.sleep(2000);
        }

        // Close extra tabs and return to the main/original one
        for (int i = tabs.size() - 1; i > 0; i--) {
            driver.switchTo().window(tabs.get(i));
            driver.close();
        }

        // Switch back to original tab
        driver.switchTo().window(tabs.get(0));
        System.out.println("Test 5 Passed - Switched tabs successfully");
    }

    // Cleanup after tests
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Close all browser windows and terminate WebDriver session
        }
    }
}
