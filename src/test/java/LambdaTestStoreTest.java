import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class LambdaTestStoreTest {
    WebDriver driver;
    WebDriverWait wait;

@BeforeMethod
    public void setup (){

    driver =new ChromeDriver();
    driver.get("https://ecommerce-playground.lambdatest.io/");
    driver.manage().window().maximize();
    wait = new WebDriverWait(driver, Duration.ofSeconds(15));


}

@Test
    public void verifyProductSearchAndAddToCart(){

    WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("search")));
    searchInput.sendKeys("Phone");

    WebElement search = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Search']")));
    search.click();


    WebElement cart = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@title='Add to Cart'])[1]")));

    org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
    js.executeScript("arguments[0].click();", cart);

    //WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(
            //By.xpath("//div[contains(@class, 'alert-success')]")));

    //String messageText = successMessage.getText();
    //System.out.println("Notification Text: " + messageText);
    //Assert.assertTrue(messageText.contains("Success"), "BUG: Success message is not displayed!");

    WebElement cartBadge = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[contains(@class, 'cart-item-total')])[1]")));

    wait.until(ExpectedConditions.textToBePresentInElement(cartBadge, "1"));

    Assert.assertEquals(cartBadge.getText().trim(), "1", "BUG: Cart badge count is incorrect!");






}

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}
