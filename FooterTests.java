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

public class FooterTests {
    static WebDriver driver;

    @BeforeClass
    public void setup() throws InterruptedException {
        // Prepare 'driver' for use
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\jegmc\\C file\\chromedriver2.exe");
        driver = new ChromeDriver();
        Thread.sleep(2000);
    }

    @Test(priority = 1)
    public void testPrivacyPolicyLink() throws InterruptedException {
        driver.get("https://aws.amazon.com/free/");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        WebElement privacyLink = driver.findElement(By.linkText("Privacy"));
        privacyLink.click();
        Thread.sleep(3000);

        String currentURL = driver.getCurrentUrl();
        assert currentURL.toLowerCase().contains("privacy") : "Privacy Policy page did not open.";
        System.out.println("Privacy Policy link works.");
        Thread.sleep(2000);
        System.out.println("Test 1 Passed (footer)");
    }

    @Test(priority = 2)
    public void testTermsOfServiceLink() throws InterruptedException {
        driver.get("https://aws.amazon.com/free/");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(1500);

        WebElement termsLink = driver.findElement(By.linkText("Site Terms"));
        termsLink.click();
        Thread.sleep(3000);

        String currentURL = driver.getCurrentUrl();
        assert currentURL.toLowerCase().contains("terms") : "Terms of Service page did not open.";
        System.out.println("Terms of Service link works.");
        Thread.sleep(2000);
        System.out.println("Test 2 Passed (footer)");
    }

    @Test(priority = 3)
    public void testContactUsLink() throws InterruptedException {
        driver.get("https://aws.amazon.com/free/");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(1500);

        WebElement contactLink = driver.findElement(By.linkText("Contact Us"));
        contactLink.click();
        Thread.sleep(3000);

        String currentURL = driver.getCurrentUrl();
        assert currentURL.toLowerCase().contains("contact") : "Contact Us page did not load.";
        System.out.println("Contact Us link works.");
        Thread.sleep(2000);
        System.out.println("Test 3 Passed (footer)");
    }

    @Test(priority = 4)
    public void testSocialIconsPresence() throws InterruptedException {
        driver.get("https://aws.amazon.com/free/");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(2000);

        List<WebElement> socialLinks = driver.findElements(By.xpath("//footer//a[contains(@href, 'twitter.com') or contains(@href, 'linkedin.com') or contains(@href, 'facebook.com')]"));

        assert socialLinks.size() > 0 : "No social media icons (links) found in the footer.";
        for (WebElement link : socialLinks) {
            System.out.println("Found social link: " + link.getAttribute("href"));
        }
        Thread.sleep(2000);
        System.out.println("Test 4 Passed (footer)");
    }

    @Test(priority = 5)
    public void testFooterTextContent() throws InterruptedException {
        driver.get("https://aws.amazon.com/free/");
        driver.manage().window().maximize();
        Thread.sleep(2000);

        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight)");
        Thread.sleep(1500);

        WebElement footer = driver.findElement(By.tagName("footer"));
        String footerText = footer.getText().toLowerCase();

        assert footerText.contains("©") || footerText.contains("amazon web services") : "Footer text content missing expected info.";
        System.out.println("Footer contains expected copyright.");
        Thread.sleep(2000);
        System.out.println("Test 5 Passed (footer)\n");
    }

    @AfterClass
    public void driverQuit() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(2000);
            driver.quit();
        }
    }
}
