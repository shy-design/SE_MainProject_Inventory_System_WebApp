import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

import java.util.List;

public class UpdatingItemTest {

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

        //Updating Item
        Thread.sleep(3000);
        WebElement updatingItemElement = driver.findElement(By.xpath("//body[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/form[1]/input[2]"));
        updatingItemElement.sendKeys("1");
        WebElement updatingButtonElement = driver.findElement(By.xpath("//body[1]/div[2]/table[1]/tbody[1]/tr[1]/td[2]/form[1]/button[1]"));
        updatingButtonElement.click();

        //Verifying the Update Item Home Page
        Thread.sleep(2000);
        WebElement itemNoElement = driver.findElement(By.xpath("//body[1]/div[1]/form[1]/table[1]/tbody[1]/tr[1]/td[2]/p[1]"));
        String actualResult = itemNoElement.getText();
        String expectedResult = "1";
        Assert.assertEquals(expectedResult, actualResult);

        Thread.sleep(2000);
        //Filling out the form
        WebElement brandElement = driver.findElement(By.xpath("//body[1]/div[1]/form[1]/table[1]/tbody[1]/tr[2]/td[2]/input[1]"));
        brandElement.clear();
        brandElement.sendKeys("Barbie Barbie");
        WebElement nameElement = driver.findElement(By.xpath("//body[1]/div[1]/form[1]/table[1]/tbody[1]/tr[3]/td[2]/input[1]"));
        nameElement.clear();
        nameElement.sendKeys("Home Box");
        WebElement quantityElement = driver.findElement(By.xpath("//body[1]/div[1]/form[1]/table[1]/tbody[1]/tr[4]/td[2]/input[1]"));
        quantityElement.clear();
        Thread.sleep(1000);
        quantityElement.sendKeys("10");
        WebElement soldElement = driver.findElement(By.xpath("//body[1]/div[1]/form[1]/table[1]/tbody[1]/tr[5]/td[2]/input[1]"));
        soldElement.clear();
        Thread.sleep(1000);
        soldElement.sendKeys("7");
        WebElement priceElement = driver.findElement(By.xpath("//body[1]/div[1]/form[1]/table[1]/tbody[1]/tr[6]/td[2]/input[1]"));
        priceElement.clear();
        Thread.sleep(1000);
        priceElement.sendKeys("90000");
        //body[1]/div[1]/form[1]/button[1]
        WebElement updateElement = driver.findElement(By.xpath("//body[1]/div[1]/form[1]/button[1]"));
        updateElement.click();

        Thread.sleep(2000);


        //Verifying the Updated Row of the Toys Table
        String[] toyRowValues= {
                "1", "Barbie Barbie", "Home Box", "10", "7", "3","90000.0",
        };
        for(int i = 1; i<=8;i++){
            Thread.sleep(1000);
            WebElement rowElement = driver.findElement(By.xpath("//body[1]/div[1]/table[1]/tbody[1]/tr[1]/td[" + i +"]"));
            Assert.assertEquals(toyRowValues[i-1], rowElement.getText());
        }

        Thread.sleep(3000);
        driver.quit();

    }


}
