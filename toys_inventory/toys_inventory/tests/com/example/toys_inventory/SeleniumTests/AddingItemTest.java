import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

import java.util.List;

public class AddingItemTest {

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

        //Adding new Item Button
        Thread.sleep(3000);
        WebElement addingItemElement = driver.findElement(By.xpath("//body[1]/div[2]/table[1]/tbody[1]/tr[1]/td[1]/form[1]/button[1]"));
        addingItemElement.click();

        //Verifying the Add Item Home Page
        Thread.sleep(2000);
        WebElement titleAddElement = driver.findElement(By.xpath("//body[1]/div[1]/form[1]/h2[1]"));
        String actualResult = titleAddElement.getText();
        String expectedResult = "Enter the information of the added item";
        Assert.assertEquals(expectedResult, actualResult);

        Thread.sleep(2000);
        //Filling out the form
        WebElement brandElement = driver.findElement(By.xpath("//body[1]/div[1]/form[1]/table[1]/tbody[1]/tr[1]/td[2]/input[1]"));
        brandElement.sendKeys("Nerf");
        WebElement nameElement = driver.findElement(By.xpath("//body[1]/div[1]/form[1]/table[1]/tbody[1]/tr[2]/td[2]/input[1]"));
        nameElement.sendKeys("Toy Gun");
        WebElement quantityElement = driver.findElement(By.xpath("//body[1]/div[1]/form[1]/table[1]/tbody[1]/tr[3]/td[2]/input[1]"));
        quantityElement.clear();
        Thread.sleep(1000);
        quantityElement.sendKeys("5");
        WebElement soldElement = driver.findElement(By.xpath("//body[1]/div[1]/form[1]/table[1]/tbody[1]/tr[4]/td[2]/input[1]"));
        soldElement.clear();
        Thread.sleep(1000);
        soldElement.sendKeys("4");
        WebElement priceElement = driver.findElement(By.xpath("//body[1]/div[1]/form[1]/table[1]/tbody[1]/tr[5]/td[2]/input[1]"));
        priceElement.clear();
        Thread.sleep(1000);
        priceElement.sendKeys("40000");
        //body[1]/div[1]/form[1]/button[1]
        WebElement addItemElement = driver.findElement(By.xpath("//body[1]/div[1]/form[1]/button[1]"));
        addItemElement.click();

        Thread.sleep(2000);


        //Verifying the Item is Added
        List<WebElement> columns = driver.findElements(By.xpath("//thead/tr/th"));
        //System.out.println("No of Rows are : " +columns.size());

        List<WebElement> rows = driver.findElements(By.xpath("//body[1]/div[1]/table[1]/tbody[1]/tr"));
        int lastElementNo = rows.size() - 1;

        //System.out.println("No of Rows are : " +rows.size());
       //System.out.println("No of Rows are : " +lastElementAdded);
        String[] lastAddedElementValues = {
                String.valueOf(lastElementNo), "Nerf", "Toy Gun", "5", "4", "1", "40000.0"
        };
        for(int i = 1; i< columns.size();i++){
            if(i == 1){
                continue;
            }else {
                WebElement lastElementAdded = driver.findElement(By.xpath("//body[1]/div[1]/table[1]/tbody[1]/tr[" + lastElementNo + "]/td[" + i + "]"));
                String actualValue = lastElementAdded.getText().toString();
                String expectedValue = lastAddedElementValues[i - 1];
                Assert.assertEquals(expectedValue, actualValue);
            }
        }

        Thread.sleep(3000);
        driver.quit();

    }

}
