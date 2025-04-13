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
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.testng.annotations.Test;


public class HomePageTests {
    static WebDriver driver;

    @BeforeClass
    public void setup() throws InterruptedException {
        // Prepare 'driver' for use
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\jegmc\\C file\\chromedriver2.exe");
        driver = new ChromeDriver();
        Thread.sleep(2000);
    }

    // Test 1: Check that the page title is correct
    @Test(priority = 1)
    public void testPageTitle() throws InterruptedException {
        driver.get("https://aws.amazon.com/free/");
        driver.manage().window().maximize();
        Thread.sleep(2000);
        String expectedTitle = "Free Cloud Computing Services - AWS Free Tier";
        String actualTitle = driver.getTitle(); // Get the actual page title
        Assert.assertEquals(actualTitle, expectedTitle, "Title does not match"); // Assert it matches expected
        System.out.println("Page title validated: " + actualTitle);
        System.out.println("Test 1 Passed (homepage)");
        Thread.sleep(2000);
    }

    // Test 2: Check that the current URL contains the expected path
    @Test(priority = 2)
    public void testCurrentURL() throws InterruptedException {
        driver.get("https://aws.amazon.com/free/");
        driver.manage().window().maximize();
        Thread.sleep(2000);
        String currentUrl = driver.getCurrentUrl(); // Get current URL
        Assert.assertNotNull(currentUrl, "Current URL is null"); // Check it's not null
        Assert.assertTrue(currentUrl.contains("aws.amazon.com/free"), "URL does not contain expected text"); // Check it includes expected string
        System.out.println("Current URL validated: " + currentUrl);

        System.out.println("Test 2 Passed (homepage)");
        Thread.sleep(2000);
    }

    // Test 3: Verify the hero image is visible on the homepage
    @Test(priority = 3)
    public void testHeroImageVisibility() throws InterruptedException {
        driver.get("https://aws.amazon.com/free/");
        driver.manage().window().maximize();
        Thread.sleep(2000);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Create explicit wait
        WebElement heroImage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.cssSelector("img[src*='Free-Tier']")) // Wait for hero image
        );
        Assert.assertTrue(heroImage.isDisplayed(), "Hero image is not visible"); // Assert it's visible
        System.out.println("Hero image is visible on the homepage.");

        System.out.println("Test 3 Passed (homepage)");
        Thread.sleep(2000);
    }

    // Test 4: Scroll to the bottom of the page and verify the footer is visible
    @Test(priority = 4)
    public void testScrollToBottom() throws InterruptedException {
        driver.get("https://aws.amazon.com/free/");
        driver.manage().window().maximize();
        Thread.sleep(2000);
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);"); // Scroll to bottom
        Thread.sleep(2000); // Wait for footer to load
        WebElement footer = driver.findElement(By.tagName("footer")); // Locate footer
        Assert.assertTrue(footer.isDisplayed(), "Footer is not visible after scrolling"); // Assert it's visible
        System.out.println("Scrolled to bottom. Footer is visible.");

        System.out.println("Test 4 Passed (homepage)");
        Thread.sleep(2000);    }

    // Test 5: Take a screenshot of the homepage and save it with a timestamp
    @Test(priority = 5)
    public void testScreenshotOfHomePage() throws IOException, InterruptedException {
        driver.get("https://aws.amazon.com/free/");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        String timestamp = String.valueOf(System.currentTimeMillis());
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destinationFolder = new File("C:\\Users\\jegmc\\Selenium SCs");
        File destination = new File(destinationFolder, "HomePageScreenshot.png");
        FileHandler.copy(screenshot, destination);
        Assert.assertTrue(destination.exists(), "Screenshot file was not created");

        System.out.println("Screenshot saved as: " + destination.getName());
        System.out.println("Test 5 Passed (homepage)\n");
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
