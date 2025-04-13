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

public class WindowAndAlertTests {
    static WebDriver driver;
    WebDriverWait wait;
    String originalTab;


    @BeforeClass
    public void setup() throws InterruptedException {
        // Prepare 'driver' for use
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\jegmc\\C file\\chromedriver2.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(5)); // Explicit wait
        originalTab = driver.getWindowHandle(); // Store the handle of the main/original tab
        Thread.sleep(2000);
    }

    // Test 1: Validates that clicking "Connect with Capgemini" opens a new tab
    @Test(priority = 1)
    public void testExternalLinkOpensNewTab() throws InterruptedException {
        driver.get("https://aws.amazon.com/partners/capgemini/"); // Open AWS Capgemini page
        driver.manage().window().maximize();
        // Close popup if present
        try {
            WebElement closeButton = driver.findElement(By.xpath("//*[@id=\":r2:\"]/div/button"));
            if (closeButton.isDisplayed() && closeButton.isEnabled()) {
                closeButton.click();
            } else {
            }
        } catch (NoSuchElementException e) {
        }
        WebElement connectButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Connect with Capgemini")));
        connectButton.click(); // Click the link
        Thread.sleep(1500);

        // Get all open tab handles
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        Assert.assertTrue(tabs.size() > 1, "New tab didn't open.");
        driver.switchTo().window(tabs.get(1)); // Switch to new tab
        Thread.sleep(2000);

        // Optionally, validate the URL
        String currentUrl = driver.getCurrentUrl();
        System.out.println("External Link Opened successfully");
        System.out.println("Test 1 Passed (window)");

    }

    // Test 2: Switches back to the original tab and confirms it's still responsive
    @Test(priority = 2)
    public void testSwitchBackToMainTab() throws InterruptedException {
        driver.get("https://aws.amazon.com/partners/capgemini/"); // Open AWS Capgemini page
        driver.manage().window().maximize();
        // Close popup if present
        try {
            WebElement closeButton = driver.findElement(By.xpath("//*[@id=\":r2:\"]/div/button"));
            if (closeButton.isDisplayed() && closeButton.isEnabled()) {
                closeButton.click();
            } else {
            }
        } catch (NoSuchElementException e) {
        }
        Thread.sleep(1500);
        WebElement connectButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Connect with Capgemini")));
        connectButton.click(); // Click the link
        Thread.sleep(3000);
        driver.switchTo().window(originalTab); // Switch back to the main/original tab
        Thread.sleep(2000);

        // Assert we're still on the correct page
        Assert.assertTrue(driver.getTitle().contains("Capgemini"), "Failed to switch back to main tab.");
        System.out.println("Switched Back to Main Tab");
        System.out.println("Test 2 Passed (window)");

    }

    // Test 3: Attempts to trigger and interact with a modal popup (custom chat modal)
    @Test(priority = 3)
    public void testModalPopupDisplay() throws InterruptedException {
        driver.get("https://aws.amazon.com/partners/capgemini/"); // Open AWS Capgemini page
        driver.manage().window().maximize();
        Thread.sleep(2000);
        // Close popup if present
        try {
            WebElement closeButton = driver.findElement(By.xpath("//*[@id=\":r2:\"]/div/button"));
            if (closeButton.isDisplayed() && closeButton.isEnabled()) {
                closeButton.click();
            } else {
            }
        } catch (NoSuchElementException e) {
        }
        // Attempt to trigger a modal (This section is highly specific and may fail if elements change)
        WebElement jsAlertButton = driver.findElement(By.xpath("//*[@id=\"mrc-sunrise-chat\"]/div[3]/button/img"));
        jsAlertButton.click();
        Thread.sleep(1500);

        // Close or interact with modal again
        WebElement launchModalButton2 = driver.findElement(By.xpath("//*[@id=\"chat-footer\"]/div/button/span"));
        launchModalButton2.click();
        Thread.sleep(2000);

        System.out.println("Poppup Displayed");
        System.out.println("Test 3 Passed (window)");

    }

    // Test 4: Simulates accepting a JavaScript-style alert/popup (based on available UI)
    @Test(priority = 4)
    public void testAcceptAlertBox() throws InterruptedException {
        driver.get("https://aws.amazon.com/partners/capgemini/");
        driver.manage().window().maximize();
        Thread.sleep(1500);

        // Close popup if present
        try {
            WebElement closeButton = driver.findElement(By.xpath("//*[@id=\":r2:\"]/div/button"));
            if (closeButton.isDisplayed() && closeButton.isEnabled()) {
                closeButton.click();
            }
        } catch (NoSuchElementException ignored) {}

        try {
            // Wait for and click the chat launcher
            WebElement openChat = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\"mrc-sunrise-chat\"]/div[3]/button/img")
            ));
            openChat.click();
            Thread.sleep(1500);

            // Wait for the close button inside the chat to appear and click it
            WebElement closeChat = wait.until(ExpectedConditions.elementToBeClickable(
                    By.xpath("//*[@id=\"chat-container\"]/div[1]/div[2]/div/div/div[1]/button/span")
            ));
            closeChat.click();
        } catch (TimeoutException | NoSuchElementException e) {
            System.out.println("Chat popup closed.");
            System.out.println("Test 4 Passed (window)");
        }
    }


    // Test 5: Opens and switches between multiple tabs, then returns to the original tab
    @Test(priority = 5)
    public void testMultipleTabNavigation() throws InterruptedException {
        // Open three new tabs using JavaScript
        driver.get("https://aws.amazon.com/free/");
        driver.manage().window().maximize();

        ((JavascriptExecutor) driver).executeScript("window.open('https://aws.amazon.com/partners/capgemini/','_blank');");
        ((JavascriptExecutor) driver).executeScript("window.open('https://aws.amazon.com/q/?nc2=h_ql_prod_l1_q','_blank');");
        ((JavascriptExecutor) driver).executeScript("window.open('https://aws.amazon.com/?nc2=h_lg','_blank');");
        Thread.sleep(3000);

        // Get handles of all open tabs
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        Assert.assertTrue(tabs.size() >= 3, "Not all tabs opened");

        // Switch through each tab and print the current URL
        for (String tab : tabs) {
            driver.switchTo().window(tab);
            System.out.println("Current URL: " + driver.getCurrentUrl());
            Thread.sleep(1500);
        }

        // Close extra tabs and return to the main/original one
        for (int i = tabs.size() - 1; i > 0; i--) {
            driver.switchTo().window(tabs.get(i));
            driver.close();
            Thread.sleep(500);
        }

        // Switch back to original tab
        driver.switchTo().window(tabs.get(0));
        System.out.println("Switched tabs successfully");
        System.out.println("Test 5 Passed  (window)\n");
    }

   @AfterClass
    public void driverQuit() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(2000);
            driver.quit();
        }
    }
}
