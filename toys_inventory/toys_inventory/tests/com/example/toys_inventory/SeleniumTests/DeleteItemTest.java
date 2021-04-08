import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

import java.util.List;

public class DeleteItemTest {
    public static void main(String[] args) throws InterruptedException {

        //Setting up the Chrome WebDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.get("http://localhost:8080/");

        //Creating a Wait for an Element to be Shown
        WebDriverWait w = new WebDriverWait(driver, 3);

        Thread.sleep(2000);
        //Using Xpath to find Elements as for better use, most elements are dynamically loaded.
        //Waiting for Login Elements
        WebElement emailElement = driver.findElement(By.xpath("//body[1]/form[1]/input[1]"));
        emailElement.sendKeys("abc@gmail.com");
        WebElement passwordElement = driver.findElement(By.xpath("//body[1]/form[1]/input[2]"));
        passwordElement.sendKeys("password");

        Thread.sleep(2000);
        //Logging in to the WebPage
        WebElement loginElement = driver.findElement(By.xpath("//body[1]/form[1]/button[1]"));
        loginElement.click();

        Thread.sleep(3000);
        //Waiting for the Toys and Games Button
        w.until(ExpectedConditions.presenceOfElementLocated (By.xpath("//body[1]/form[1]/button[1]")));
        WebElement toysInventoryElement = driver.findElement(By.xpath("//body[1]/form[1]/button[1]"));
        toysInventoryElement.click();

        //Deleting Last Item and Verifying the Values are not the same
        List<WebElement> rows = driver.findElements(By.xpath("//body[1]/div[1]/table[1]/tbody[1]/tr"));
        int lastElementNo = rows.size() - 1;

        Thread.sleep(3000);
        WebElement deletingItemElement = driver.findElement(By.xpath("//body[1]/div[2]/table[1]/tbody[1]/tr[1]/td[3]/form[1]/input[2]"));
        deletingItemElement.sendKeys(String.valueOf(lastElementNo));
        WebElement deletingButtonElement = driver.findElement(By.xpath("//body[1]/div[2]/table[1]/tbody[1]/tr[1]/td[3]/form[1]/button[1]"));
        deletingButtonElement.click();


        List<WebElement> rowsNew = driver.findElements(By.xpath("//body[1]/div[1]/table[1]/tbody[1]/tr"));
        int lastElementNoNew = rowsNew.size() - 1;

            WebElement lastElementAdded = driver.findElement(By.xpath("//body[1]/div[1]/table[1]/tbody[1]/tr["+ lastElementNoNew + "]/td[1]"));
            String actualValue = lastElementAdded.getText().toString();
            String expectedValue = String.valueOf(lastElementNo);
            Assert.assertNotEquals(expectedValue, actualValue);

        Thread.sleep(3000);
        driver.quit();

    }


}
