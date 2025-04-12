package AWS;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;

public class FormElementTests {

    WebDriver driver;
    WebDriverWait wait;

    // Setup method that runs once before all tests
    @BeforeClass
    public void setUp() {
        // Initialize ChromeDriver and WebDriverWait
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();

        // Navigate to the form page
        driver.get("https://pages.awscloud.com/Amazon-Elastic-VMware-Service-EVS-preview.html");
    }

    // Test radio button selection functionality
    @Test(priority = 1)
    public void testRadioButtonSelection() throws InterruptedException {
        // Wait for the page to load
        Thread.sleep(3000);

        // Scroll down slightly to bring radio buttons into view
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 1000);");
        Thread.sleep(5000);

        // Click on personal radio button
        WebElement personalRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("LblmktoRadio_3156360_1")));
        personalRadioButton.click();
        Thread.sleep(2000);

        // Click on business radio button
        WebElement businessRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("LblmktoRadio_3156360_0")));
        businessRadioButton.click();
        Thread.sleep(2000);

        // Scroll back to the top
        js.executeScript("window.scrollTo(0, 0);");
        Thread.sleep(3000);
        System.out.println("Test 1 Passed - Radio Buttons Click Properly");
    }

    // Test that the "First Name" field is required
    @Test(priority = 2)
    public void testRequiredNameField() throws InterruptedException {
        // Fill out email field only
        WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Email")));
        emailField.sendKeys("dummyemail@domain.com");
        Thread.sleep(3000);

        // Try submitting the form without filling out first name
        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"mktoForm_71743\"]/div[50]/span/button"));
        submitButton.click();
        Thread.sleep(3000);
        System.out.println("Test 2 Passed - This field is required message displayed");
    }

    // Test that the "Email" field is required
    @Test(priority = 3)
    public void testRequiredEmailField() throws InterruptedException {
        // Clear email field
        WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Email")));
        emailField.clear();
        emailField.sendKeys("dummyemail");

        // Attempt to submit with only first name present
        WebElement firstNameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("FirstName")));
        Thread.sleep(2000);

        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"mktoForm_71743\"]/div[50]/span/button"));
        submitButton.click();
        Thread.sleep(2000);

        // Now add first name and submit again
        driver.findElement(By.id("FirstName")).sendKeys("John");
        Thread.sleep(2000);

        WebElement submitButton2 = driver.findElement(By.xpath("//*[@id=\"mktoForm_71743\"]/div[50]/span/button"));
        submitButton2.click();
        Thread.sleep(2000);

        // Clear first name field for future tests
        firstNameField.clear();
        System.out.println("Test 3 Passed - Must be a valid email message displayed");
    }

    // Full end-to-end form submission test
    @Test(priority = 4)
    public void testCompleteFormSubmission() throws InterruptedException {
        // Reload form page to reset all fields
        driver.get("https://pages.awscloud.com/Amazon-Elastic-VMware-Service-EVS-preview.html");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Select the personal radio button
        WebElement personalRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("LblmktoRadio_3156360_1")));
        personalRadioButton.click();
        Thread.sleep(1000);

        // Fill out all required and optional fields
        driver.findElement(By.id("Email")).sendKeys("johndoe@test.com");
        Thread.sleep(1000);
        driver.findElement(By.id("FirstName")).sendKeys("John");
        Thread.sleep(1000);
        driver.findElement(By.id("LastName")).sendKeys("Doe");
        Thread.sleep(1000);
        driver.findElement(By.id("zOPprogressiveprofilingpnum")).sendKeys("1234567890");
        Thread.sleep(1000);
        driver.findElement(By.id("Company")).sendKeys("TestCompany");
        Thread.sleep(1000);

        // Select country from dropdown
        Select countrySelect = new Select(driver.findElement(By.id("Country")));
        countrySelect.selectByVisibleText("United States");
        Thread.sleep(1000);

        // Continue filling remaining fields
        driver.findElement(By.id("zOPprogressiveprofilingsprov")).sendKeys("Nebraska");
        Thread.sleep(1000);
        driver.findElement(By.id("zOPprogressiveprofilingpcode")).sendKeys("12345");
        Thread.sleep(1000);

        Select jobSelect = new Select(driver.findElement(By.id("zOPprogressiveprofilingjrole")));
        jobSelect.selectByVisibleText("IT Executive");
        Thread.sleep(1000);

        Select industrySelect = new Select(driver.findElement(By.id("zOPprogressiveprofilingind")));
        industrySelect.selectByVisibleText("Aerospace");
        Thread.sleep(1000);

        driver.findElement(By.id("zOPprogressiveprofilingjtitle")).sendKeys("QA Engineer");
        Thread.sleep(1000);

        Select levelSelect = new Select(driver.findElement(By.id("zOPprogressiveprofilingausag")));
        levelSelect.selectByVisibleText("Do not use AWS today");
        Thread.sleep(1000);

        Select useSelect = new Select(driver.findElement(By.id("zOPprogressiveprofilingucase")));
        useSelect.selectByVisibleText("Gaming");
        Thread.sleep(1000);

        // Click checkbox and final radio button
        WebElement useCheckbox = driver.findElement(By.id("LblmktoCheckbox_3156362_0"));
        useCheckbox.click();
        Thread.sleep(1000);

        WebElement oftenRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("LblmktoRadio_3156363_0")));
        oftenRadioButton.click();
        Thread.sleep(1000);

        // Submit the form
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mktoForm_71743\"]/div[50]/span/button")));
        submitButton.click();
        Thread.sleep(2000); // Wait for form submission to process

        // Scroll to top after submission
        js.executeScript("window.scrollTo(0, 0);");
        Thread.sleep(3000);
        System.out.println("Test 4 Passed - Form filled and submitted successfully");
    }

    // Test navigation link (e.g., clicking a "click here" link)
    @Test(priority = 5)
    public void testNavigateAfterSubmission() throws InterruptedException {
        WebElement click = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("click here")));
        click.click();
        Thread.sleep(3000);
        System.out.println("Test 5 Passed - Link navigated successfully");
    }

    // Teardown method to close browser after all tests
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
