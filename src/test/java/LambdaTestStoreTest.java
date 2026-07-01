import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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
    public void setup() {

        driver = new ChromeDriver();
        driver.get("https://ecommerce-playground.lambdatest.io/");
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));


    }

    @Test
    public void verifyProductSearchAndAddToCart() {

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


    @Test

    public void verifyProductCheckoutProcess() {

        // 1. Phone search
        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("search")));
        searchInput.sendKeys("Phone");

        WebElement search = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Search']")));
        search.click();

        // 2. Add to Cart
        WebElement cart = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@title='Add to Cart'])[1]")));
        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", cart);

        // 3. Cart Badge
        WebElement cartBadge = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//span[contains(@class, 'cart-item-total')])[1]")));
        wait.until(ExpectedConditions.textToBePresentInElement(cartBadge, "1"));
        Assert.assertEquals(cartBadge.getText().trim(), "1", "BUG: Cart badge count is incorrect!");

        // 4.
        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=checkout/cart");

        // 5. Estimate Shipping section
        WebElement cuppon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//h5[contains(text(),'Estimate Shipping')]")));
        cuppon.click();

        // 6.United Kingdom
        WebElement country = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-country")));
        org.openqa.selenium.support.ui.Select selectCountry = new org.openqa.selenium.support.ui.Select(country);
        selectCountry.selectByVisibleText("United Kingdom");

        // 7. (id=input-zone)
        WebElement state = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-zone")));
        org.openqa.selenium.support.ui.Select selectState = new org.openqa.selenium.support.ui.Select(state);
        selectState.selectByVisibleText("Scottish Borders");

        // 8. Postal Code
        WebElement postal = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-postcode")));
        postal.clear();
        postal.sendKeys("TD1 1AA");

        // 9. Get Quotes
        WebElement getquote = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Get Quotes']")));
        getquote.click();

        // 10. Shipping Method - (Flat Shipping) select
        WebElement flat_ship = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@name='shipping_method']")));
        if (!flat_ship.isSelected()) {
            flat_ship.click();
        }

        // 11. Apply Shipping
        WebElement apply_ship = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Apply Shipping']")));
        apply_ship.click();

        // Waiting
       /* try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } */

        wait.until(ExpectedConditions.urlContains("checkout"));

        // 12. new page find the checkout
        WebElement finalCheckout = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Checkout']")));

        // click fina; checkout
        js.executeScript("arguments[0].scrollIntoView(true);", finalCheckout);
        js.executeScript("arguments[0].click();", finalCheckout);

    }

    @Test
    public void addCompareProcess() {

        WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(By.name("search")));
        searchInput.sendKeys("Apple");

        WebElement search = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Search']")));
        search.click();

        WebElement compare = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[@title='Compare this Product'])[1]")));

        org.openqa.selenium.JavascriptExecutor js = (org.openqa.selenium.JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", compare);

    }
}

