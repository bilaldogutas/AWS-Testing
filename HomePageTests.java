// Import necessary libraries for Selenium WebDriver, WebDriverManager, TestNG, and file handling
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;

// Main test class for AWS Free Tier homepage tests
public class HomePageTests {

    private WebDriver driver; // WebDriver instance for browser automation

    // Runs once before all test methods in the class
    @BeforeClass
    public void setUp() throws InterruptedException {
        // Setup ChromeDriver using WebDriverManager
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        // Maximize browser window and navigate to AWS Free Tier page
        driver.manage().window().maximize();
        driver.get("https://aws.amazon.com/free/");
        Thread.sleep(2000); // Pause for 2 seconds to allow page to load
        System.out.println("✅ Browser launched and navigated to AWS Free Tier homepage.");
    }

    // Test 1: Check that the page title is correct
    @Test(priority = 1)
    public void testPageTitle() throws InterruptedException {
        Thread.sleep(1000);
        String expectedTitle = "Free Cloud Computing Services - AWS Free Tier";
        String actualTitle = driver.getTitle(); // Get the actual page title
        Assert.assertEquals(actualTitle, expectedTitle, "Title does not match"); // Assert it matches expected
        System.out.println("✅ Page title validated: " + actualTitle);
        Thread.sleep(1000);
    }

    // Test 2: Check that the current URL contains the expected path
    @Test(priority = 2)
    public void testCurrentURL() throws InterruptedException {
        Thread.sleep(1000);
        String currentUrl = driver.getCurrentUrl(); // Get current URL
        Assert.assertNotNull(currentUrl, "Current URL is null"); // Check it's not null
        Assert.assertTrue(currentUrl.contains("aws.amazon.com/free"), "URL does not contain expected text"); // Check it includes expected string
        System.out.println("✅ Current URL validated: " + currentUrl);
        Thread.sleep(1000);
    }

    // Test 3: Verify the hero image is visible on the homepage
    @Test(priority = 3)
    public void testHeroImageVisibility() throws InterruptedException {
        Thread.sleep(1000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Create explicit wait
        WebElement heroImage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img[src*='Free-Tier']")) // Wait for hero image
        );
        Assert.assertTrue(heroImage.isDisplayed(), "Hero image is not visible"); // Assert it's visible
        System.out.println("✅ Hero image is visible on the homepage.");
        Thread.sleep(1000);
    }

    // Test 4: Scroll to the bottom of the page and verify the footer is visible
    @Test(priority = 4)
    public void testScrollToBottom() throws InterruptedException {
        Thread.sleep(1000);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);"); // Scroll to bottom
        Thread.sleep(2000); // Wait for footer to load
        WebElement footer = driver.findElement(By.tagName("footer")); // Locate footer
        Assert.assertTrue(footer.isDisplayed(), "Footer is not visible after scrolling"); // Assert it's visible
        System.out.println("✅ Scrolled to bottom. Footer is visible.");
        Thread.sleep(1000);
    }

    // Test 5: Take a screenshot of the homepage and save it with a timestamp
    @Test(priority = 5)
    public void testScreenshotOfHomePage() throws IOException, InterruptedException {
        Thread.sleep(1000);
        String timestamp = String.valueOf(System.currentTimeMillis()); // Get current time as unique file name
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); // Take screenshot
        File destination = new File("HomePageScreenshot_" + timestamp + ".png"); // Save to file
        Files.copy(screenshot.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING); // Copy file
        Assert.assertTrue(destination.exists(), "Screenshot file was not created"); // Assert file exists
        System.out.println("✅ Screenshot saved as: " + destination.getName());
        Thread.sleep(1000);
    }

    // Runs once after all test methods in the class
    @AfterClass
    public void tearDown() throws InterruptedException {
        Thread.sleep(1000);
        if (driver != null) {
            driver.quit(); // Close the browser
            System.out.println("✅ Browser closed. Test suite finished.");
        }
    }
}