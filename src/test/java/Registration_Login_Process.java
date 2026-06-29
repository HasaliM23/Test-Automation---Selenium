import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Registration_Login_Process {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeMethod
    public void setup() {

        driver = new ChromeDriver();

        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));


    }

    @Test
    public void registration_Process() {

        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=account/register");
        WebElement fname = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='input-firstname']")));
        fname.sendKeys("Michel");

        WebElement lname = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='input-lastname']")));
        lname.sendKeys("Starc");

        WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='input-email']")));
        email.sendKeys("michs@gmail.com");

        WebElement telephone = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='input-telephone']")));
        telephone.sendKeys("0712345678");

       // WebElement psw = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("///input[@id='input-password']")));

        //psw.sendKeys("12345");

        WebElement psw = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='input-password']")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",psw);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",psw );
        psw.sendKeys("12345");

        WebElement cpsw = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='input-confirm']")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",cpsw);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",cpsw );
        cpsw.sendKeys("12345");

       // WebElement cpsw = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='input-confirm']")));

       // cpsw.sendKeys("12345");

        WebElement agree = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[@for='input-agree']")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",agree);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",agree);

        //WebElement agree = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='input-agree']")));
        //agree.click();

        WebElement continous = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='submit']")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",continous);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",continous);

       // WebElement continous = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']")));
       // continous.click();

        WebElement successHeading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(),'Created!')]")));

        String expectedMessage = "Your Account Has Been Created!";
        Assert.assertEquals(successHeading.getText().trim(), expectedMessage, "BUG: Registration failed!");
    }


    @Test
    public void login_Process() {


        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=account/login");


        WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='input-email']")));
        email.sendKeys("michs@gmail.com");

        WebElement psw = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='input-password']")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",psw);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",psw );
        psw.sendKeys("12345");

        WebElement login = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']")));
        login.click();


    }

    @Test
    public void emaptyfieldTest() {

        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=account/register");


        WebElement continous = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='submit']")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",continous);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",continous);

        WebElement message = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[.='First Name must be between 1 and 32 characters!']")));

        String expectedMessage = "First Name must be between 1 and 32 characters!";
        Assert.assertEquals(message.getText().trim(), expectedMessage, "BUG: Registration failed!");

    }

    @Test
    public void email_invalid() {

        driver.get("https://ecommerce-playground.lambdatest.io/index.php?route=account/register");
        WebElement fname = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='input-firstname']")));
        fname.sendKeys("Michel");

        WebElement lname = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='input-lastname']")));
        lname.sendKeys("Starc");

        WebElement email = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='input-email']")));
        email.sendKeys("michsgmail.com");

        WebElement telephone = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='input-telephone']")));
        telephone.sendKeys("0712345678");

        // WebElement psw = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("///input[@id='input-password']")));

        //psw.sendKeys("12345");

        WebElement psw = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='input-password']")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",psw);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",psw );
        psw.sendKeys("12345");

        WebElement cpsw = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@id='input-confirm']")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",cpsw);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",cpsw );
        cpsw.sendKeys("12345");

        // WebElement cpsw = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@id='input-confirm']")));

        // cpsw.sendKeys("12345");

        WebElement agree = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[@for='input-agree']")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",agree);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",agree);

        //WebElement agree = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[@for='input-agree']")));
        //agree.click();

        WebElement continous = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@type='submit']")));
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);",continous);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();",continous);

        // WebElement continous = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']")));
        // continous.click();

       // WebElement errorWarning = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'alert-danger')]")));

        //String expectedErrorMessage = "E-Mail Address does not appear to be valid!";
        //Assert.assertEquals(errorWarning.getText().trim(), expectedErrorMessage, "BUG: Validation error message is wrong!");


    }










    }


