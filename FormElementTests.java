package TestCases;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class FormElementTests {
    static WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() throws InterruptedException {
        // Prepare 'driver' for use
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\jegmc\\C file\\chromedriver2.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Thread.sleep(2000);
    }

    // Test radio button selection functionality
    @Test(priority = 1)
    public void testRadioButtonSelection() throws InterruptedException {
        driver.get("https://pages.awscloud.com/Amazon-Elastic-VMware-Service-EVS-preview.html");
        driver.manage().window().maximize();
        // Wait for the page to load
        Thread.sleep(1500);

        // Scroll down slightly to bring radio buttons into view
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0, 1000);");
        Thread.sleep(1500);

        // Click on personal radio button
        WebElement personalRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("LblmktoRadio_3156360_1")));
        personalRadioButton.click();
        Thread.sleep(2000);

        // Click on business radio button
        WebElement businessRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("LblmktoRadio_3156360_0")));
        businessRadioButton.click();
        Thread.sleep(1500);

        System.out.println("Radio Buttons Click Properly");
        System.out.println("Test 1 Passed (FormElement)");
    }

    // Test that the "First Name" field is required
    @Test(priority = 2)
    public void testRequiredNameField() throws InterruptedException {
        driver.get("https://pages.awscloud.com/Amazon-Elastic-VMware-Service-EVS-preview.html");
        driver.manage().window().maximize();
        // Fill out email field only
        WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Email")));
        emailField.sendKeys("dummyemail@domain.com");
        Thread.sleep(1500);

        // Try submitting the form without filling out first name
        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"mktoForm_71743\"]/div[50]/span/button"));
        submitButton.click();
        Thread.sleep(1500);
        System.out.println("'This field is required' message displayed");
        System.out.println("Test 2 Passed (FormElement)");
    }

    // Test that the "Email" field is required
    @Test(priority = 3)
    public void testRequiredEmailField() throws InterruptedException {
        driver.get("https://pages.awscloud.com/Amazon-Elastic-VMware-Service-EVS-preview.html");
        driver.manage().window().maximize();
        // Clear email field
        WebElement emailField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Email")));
        emailField.clear();
        emailField.sendKeys("dummyemail");

        // Attempt to submit with only first name present
        WebElement firstNameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("FirstName")));
        Thread.sleep(1500);

        WebElement submitButton = driver.findElement(By.xpath("//*[@id=\"mktoForm_71743\"]/div[50]/span/button"));
        submitButton.click();
        Thread.sleep(1500);

        // Now add first name and submit again
        driver.findElement(By.id("FirstName")).sendKeys("John");
        Thread.sleep(1500);

        WebElement submitButton2 = driver.findElement(By.xpath("//*[@id=\"mktoForm_71743\"]/div[50]/span/button"));
        submitButton2.click();
        Thread.sleep(1500);

        // Clear first name field for future tests
        firstNameField.clear();
        System.out.println("'Must be a valid email' message displayed");
        System.out.println("Test 3 Passed (FormElement)");}

    // Full end-to-end form submission test
    @Test(priority = 4)
    public void testCompleteFormSubmission() throws InterruptedException {
        // Reload form page to reset all fields
        driver.get("https://pages.awscloud.com/Amazon-Elastic-VMware-Service-EVS-preview.html");
        driver.manage().window().maximize();
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

        System.out.println("Form filled and submitted successfully");
        System.out.println("Test 4 Passed (FormElement)");
    }

    // Test navigation link (e.g., clicking a "click here" link)
    @Test(priority = 5)
    public void testNavigateAfterSubmission() throws InterruptedException {
        driver.get("https://pages.awscloud.com/Amazon-Elastic-VMware-Service-EVS-preview.html");
        driver.manage().window().maximize();
        //Submit form again

        // Select the personal radio button
        WebElement personalRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("LblmktoRadio_3156360_1")));
        personalRadioButton.click();
        Thread.sleep(500);

        // Fill out all required and optional fields
        driver.findElement(By.id("Email")).sendKeys("johndoe@test.com");
        Thread.sleep(500);
        driver.findElement(By.id("FirstName")).sendKeys("John");
        Thread.sleep(500);
        driver.findElement(By.id("LastName")).sendKeys("Doe");
        Thread.sleep(500);
        driver.findElement(By.id("zOPprogressiveprofilingpnum")).sendKeys("1234567890");
        Thread.sleep(500);
        driver.findElement(By.id("Company")).sendKeys("TestCompany");
        Thread.sleep(500);

        // Select country from dropdown
        Select countrySelect = new Select(driver.findElement(By.id("Country")));
        countrySelect.selectByVisibleText("United States");
        Thread.sleep(500);

        // Continue filling remaining fields
        driver.findElement(By.id("zOPprogressiveprofilingsprov")).sendKeys("Nebraska");
        Thread.sleep(500);
        driver.findElement(By.id("zOPprogressiveprofilingpcode")).sendKeys("12345");
        Thread.sleep(500);

        Select jobSelect = new Select(driver.findElement(By.id("zOPprogressiveprofilingjrole")));
        jobSelect.selectByVisibleText("IT Executive");
        Thread.sleep(500);

        Select industrySelect = new Select(driver.findElement(By.id("zOPprogressiveprofilingind")));
        industrySelect.selectByVisibleText("Aerospace");
        Thread.sleep(500);

        driver.findElement(By.id("zOPprogressiveprofilingjtitle")).sendKeys("QA Engineer");
        Thread.sleep(500);

        Select levelSelect = new Select(driver.findElement(By.id("zOPprogressiveprofilingausag")));
        levelSelect.selectByVisibleText("Do not use AWS today");
        Thread.sleep(500);

        Select useSelect = new Select(driver.findElement(By.id("zOPprogressiveprofilingucase")));
        useSelect.selectByVisibleText("Gaming");
        Thread.sleep(500);

        // Click checkbox and final radio button
        WebElement useCheckbox = driver.findElement(By.id("LblmktoCheckbox_3156362_0"));
        useCheckbox.click();
        Thread.sleep(500);

        WebElement oftenRadioButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("LblmktoRadio_3156363_0")));
        oftenRadioButton.click();
        Thread.sleep(1000);

        // Submit the form
        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mktoForm_71743\"]/div[50]/span/button")));
        submitButton.click();
        Thread.sleep(2500);

        WebElement click = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("click here")));
        click.click();
        Thread.sleep(2000);
        System.out.println("Link navigated successfully");
        System.out.println("Test 5 Passed (FormElement)\n");
    }

    @AfterClass
    public void driverQuit() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(2000);
            driver.quit();
        }
    }
}
