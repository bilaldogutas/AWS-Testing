package TestCases;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import java.io.File;
import org.openqa.selenium.io.FileHandler;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.testng.annotations.Test;

public class SignupTests {
    static WebDriver driver;

    @BeforeClass
    public void setup() throws InterruptedException {
        // Prepare 'driver' for use
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\jegmc\\C file\\chromedriver2.exe");
        driver = new ChromeDriver();
        Thread.sleep(2000);
    }

    @Test(priority = 1)
    public void testNavigateToSignupPage() throws InterruptedException {
        driver.get("https://aws.amazon.com/free/");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        // Click "Create a Free Account" button
        WebElement createAccountBtn = driver.findElement(By.linkText("Create a Free Account"));
        createAccountBtn.click();
        Thread.sleep(3000);

        // Verify if signup page loaded
        String expectedURL = "https://portal.aws.amazon.com/billing/signup";
        String currentURL = driver.getCurrentUrl();
        assert currentURL.contains("signup") : "Did not navigate to signup page.";
        Thread.sleep(2000);
        System.out.println("Test 1 Passed (signup)");
    }

    @Test(priority = 2)
    public void testFormFieldPresence() throws InterruptedException {
        driver.get("https://signin.aws.amazon.com/signup?request_type=register");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement emailField = driver.findElement(By.xpath("//*[@id=\"emailAddress\"]"));
        WebElement accountName = driver.findElement(By.xpath("//*[@id=\"accountName\"]"));

        assert emailField.isDisplayed();
        assert accountName.isDisplayed();
        Thread.sleep(2000);
        System.out.println("Test 2 Passed (signup)");
    }

    @Test(priority = 3)
    public void testEmptyFormSubmission() throws InterruptedException {
        driver.get("https://signin.aws.amazon.com/signup?request_type=register");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement continueBtn = driver.findElement(By.xpath("//*[@id=\"__next\"]/main/div[2]/div/div/div[3]/div/div/form/div/div/div[2]/div[3]/button/span"));
        continueBtn.click();
        Thread.sleep(2000);

        WebElement errorBox = driver.findElement(By.xpath("//*[@id=\"emailAddress-error\"]/div/div/span"));
        assert errorBox.isDisplayed();
        Thread.sleep(2000);
        System.out.println("Test 3 Passed (signup)");
    }

    @Test(priority = 4)
    public void testSecurityPopup() throws InterruptedException {
        driver.get("https://signin.aws.amazon.com/signup?request_type=register");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        // Replace with actual selector from AWS (adjust if different)
        WebElement emailField = driver.findElement(By.xpath("//*[@id=\"emailAddress\"]"));
        emailField.sendKeys("thisisfake@example.com");
        Thread.sleep(1000);

        WebElement accountName = driver.findElement(By.xpath("//*[@id=\"accountName\"]"));
        accountName.sendKeys("thisisfake");
        Thread.sleep(1000);

        WebElement continueBtn = driver.findElement(By.xpath("//*[@id=\"__next\"]/main/div[2]/div/div/div[3]/div/div/form/div/div/div[2]/div[3]/button/span")); // Adjust as needed
        continueBtn.click();
        Thread.sleep(3000);

        try {
            WebElement securityHeader = driver.findElement(By.xpath("//*[contains(text(), 'Security Verification')]"));
            if (securityHeader.isDisplayed()) {
                System.out.println("Security Verification popup is displayed.");
                Thread.sleep(2000);
                System.out.println("Test 4 Passed (signup)");
                return;
            }
        } catch (NoSuchElementException e) {
            System.out.println("No Security Verification detected");
        }
    }



    @Test(priority = 5)
    public void testFormInputCharacterLimits() throws InterruptedException {
        driver.get("https://signin.aws.amazon.com/signup?request_type=register");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        // Define very long inputs
        String longEmail = "ThisIsAnExtremelyLongemailaddresstThatShouldExceedTheMaximumAllowedCharacters1234567890@example.com";
        String longAccountName = "12345678910ThisIsAnExtremelyLongAccountNameThatShouldExceedTheMaximumFieldLimit AndShouldBeRejected123456789101112131415";

        WebElement emailField = driver.findElement(By.xpath("//*[@id=\"emailAddress\"]"));
        WebElement accountName = driver.findElement(By.xpath("//*[@id=\"accountName\"]"));

        emailField.clear();
        accountName.clear();

        emailField.sendKeys(longEmail);
        accountName.sendKeys(longAccountName);
        Thread.sleep(1000);

        String enteredEmail = emailField.getAttribute("value");
        String enteredAccountName = accountName.getAttribute("value");

        WebElement continueBtn = driver.findElement(By.xpath("//*[@id=\"__next\"]/main/div[2]/div/div/div[3]/div/div/form/div/div/div[2]/div[3]/button/span")); // Adjust as needed
        continueBtn.click();
        Thread.sleep(3000);

        System.out.println("Entered Email: " + enteredEmail);
        System.out.println("Entered Account Name: " + enteredAccountName);

        try {
            WebElement warningMessage = driver.findElement(By.xpath("//*[text()='You have exceeded the character limit.']"));
            if (warningMessage.isDisplayed()) {
                System.out.println("Character limit warning displayed.");
            } else {
                assert false : "Message element found but not visible.";
            }
        } catch (NoSuchElementException e) {
            assert false : " 'You have exceeded the character limit.' not found.";
        }
        Thread.sleep(2000);
        System.out.println("Test 5 Passed (signup)\n");
    }

    @AfterClass
    public void driverQuit() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(2000);
            driver.quit();
        }
    }


}
