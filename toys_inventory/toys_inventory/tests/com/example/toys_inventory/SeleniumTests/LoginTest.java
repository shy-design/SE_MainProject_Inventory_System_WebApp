import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

public class LoginTest {

    public static void main(String[] args) throws InterruptedException {

        //Setting up the Chrome WebDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/");

        //Creating a Wait for an Element to be Shown
        WebDriverWait w = new WebDriverWait(driver,3);

        //w.until(ExpectedConditions.presenceOfElementLocated (By.xpath("//body[1]/form[1]/button[1]")));

        //Using Xpath to find Elements as for better use, most elements are dynamically loaded.
        //Waiting for Login Elements
        Thread.sleep(2000);
        WebElement emailElement = driver.findElement(By.xpath("//body[1]/form[1]/input[1]"));
        emailElement.sendKeys("abc@gmail.com");
        WebElement passwordElement = driver.findElement(By.xpath("//body[1]/form[1]/input[2]"));
        passwordElement.sendKeys("password");

        Thread.sleep(2000);
        //Logging in to the WebPage
        WebElement loginElement = driver.findElement(By.xpath("//body[1]/form[1]/button[1]"));
        loginElement.click();

        Thread.sleep(2000);
        //Checking We are Logged in...
        WebElement titleElement = driver.findElement(By.xpath("//body[1]/form[1]/h1[1]"));
        String actualResult = titleElement.getText();
        String expectedResult = "Welcome to the Toy inventory system!";
        Assert.assertEquals(expectedResult, actualResult);

        Thread.sleep(3000);
        driver.quit();
    }

}
