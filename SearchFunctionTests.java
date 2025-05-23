import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import org.openqa.selenium.io.FileHandler;

public class SearchFunctionTests {

    static WebDriver driver;

    @BeforeClass
    public void setup() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\pagan\\Desktop\\School\\Software Testing\\Drivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://aws.amazon.com/free/");
        driver.manage().window().maximize();
        Thread.sleep(2000);
    }

    @Test(priority = 1)
    void testSearchWithValidKeyword() throws InterruptedException {
        // click search icon
        WebElement searchIcon = driver.findElement(By.xpath("//*[@id='m-nav']/div[1]/div[2]/nav/div/button"));
        searchIcon.click();
        Thread.sleep(2000);

        // type into the search field
        WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"m-nav-desktop-search\"]/form/div/span/input[2]"));
        searchBox.sendKeys("Amazon EC2 Auto Scaling");
        Thread.sleep(2000);
        searchBox.sendKeys(Keys.ENTER);
        Thread.sleep(3500);
        WebElement popup = driver.findElement(By.xpath("//*[@id=\":r2:\"]/div/button"));
        popup.click();
        Thread.sleep(3000);

        // check for expected keywords
        String pageSource = driver.getPageSource().toLowerCase();
        assert pageSource.contains("auto scaling") || pageSource.contains("ec2") : "Expected result content not found.";

        System.out.println("Test 1 Passed");
    }

    @Test(priority = 2)
    void testEmptySearchReturnsNothing() throws InterruptedException {
        // click the search icon
        WebElement searchIcon = driver.findElement(By.xpath("//*[@id='m-nav']/div[1]/div[2]/nav/div/button"));
        searchIcon.click();
        Thread.sleep(2000);

        // type nothing and hit Enter
        WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"m-nav-desktop-search\"]/form/div/span/input[2]"));
        searchBox.clear();
        searchBox.sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        // verify that page does not crash
        String pageSource = driver.getPageSource().toLowerCase();
        assert pageSource.contains("search") || pageSource.contains("no results") : "Site should handle empty search without crashing.";

        System.out.println("Test 2 Passed");
    }

    @Test(priority = 3)
    void testSearchWithSpecialCharacters() throws InterruptedException {
        driver.get("https://aws.amazon.com/free/");
        // click the search icon
        WebElement searchIcon = driver.findElement(By.xpath("//*[@id='m-nav']/div[1]/div[2]/nav/div/button"));
        searchIcon.click();
        Thread.sleep(2000);

        // enter special characters into the search field
        WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"m-nav-desktop-search\"]/form/div/span/input[2]"));
        searchBox.clear();
        searchBox.sendKeys("!@#$%^&*");
        Thread.sleep(2000);
        searchBox.sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        // check that the site handled the input
        String pageSource = driver.getPageSource().toLowerCase();
        assert pageSource.contains("no results") || pageSource.contains("search") : "Special character search should not crash.";

        System.out.println("Test 3 Passed");
    }

    @Test(priority = 4)
    void testCaseInsensitiveSearch() throws InterruptedException {
        driver.get("https://aws.amazon.com/free/");
        // click the search icon
        WebElement searchIcon = driver.findElement(By.xpath("//*[@id='m-nav']/div[1]/div[2]/nav/div/button"));
        searchIcon.click();
        Thread.sleep(2000);

        // enter lowercase "dynamodb" into the search box
        WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"m-nav-desktop-search\"]/form/div/span/input[2]"));
        searchBox.clear();
        searchBox.sendKeys("dyanmodb");
        Thread.sleep(2000);
        searchBox.sendKeys(Keys.ENTER);
        Thread.sleep(4000);

        // verify that results include "dynamodb"
        String pageSource = driver.getPageSource().toLowerCase();
        assert pageSource.contains("dynamodb") : "Case-insensitive search failed to return results.";

        System.out.println("Test 4 Passed");
    }

    @Test(priority = 5)
    void testScreenshotOfSearchResults() throws IOException, InterruptedException {
        driver.get("https://aws.amazon.com/free/");
        // click the search icon
        WebElement searchIcon = driver.findElement(By.xpath("//*[@id='m-nav']/div[1]/div[2]/nav/div/button"));
        searchIcon.click();
        Thread.sleep(2000);

        // enter "SageMaker" into the search box
        WebElement searchBox = driver.findElement(By.xpath("//*[@id=\"m-nav-desktop-search\"]/form/div/span/input[2]"));
        searchBox.clear();
        searchBox.sendKeys("SageMaker");
        Thread.sleep(2000);
        searchBox.sendKeys(Keys.ENTER);
        Thread.sleep(3000);

        // screenshot
        File source = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destinationFolder = new File("C:\\Users\\pagan\\Desktop\\School\\Software Testing\\Project Screenshots");
        File destinationFile = new File(destinationFolder, "sagemaker.png");
        FileHandler.copy(source, destinationFile);

        System.out.println("Test 5 Passed");
    }

    @AfterClass
    public void tearDown() throws InterruptedException {
        if (driver != null) {
            Thread.sleep(2000);
            driver.quit();
        }
    }
}
