import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class wishlistTest {

    WebDriver driver;
        WebDriverWait wait;

        @BeforeMethod
        public void setup() {

            driver = new ChromeDriver();
            driver.get("https://ecommerce-playground.lambdatest.io/");
            driver.manage().window().maximize();
            wait = new WebDriverWait(driver, Duration.ofSeconds(15));


        }

        @Test
        public void addWishlistProcess() {

            WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("search")));
            searchInput.sendKeys("Apple");

            WebElement search = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Search']")));
            search.click();

            WebElement heart = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@title='Add to Wish List'])[1]")));

            org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", heart);

        }
}
