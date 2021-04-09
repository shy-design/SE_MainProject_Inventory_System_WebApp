import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

public class ToysAndGamesInventoryTest {

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

        Thread.sleep(2000);
        //Waiting for the Toys and Games Button
        w.until(ExpectedConditions.presenceOfElementLocated (By.xpath("//body[1]/form[1]/button[1]")));
        WebElement toysInventoryElement = driver.findElement(By.xpath("//body[1]/form[1]/button[1]"));
        toysInventoryElement.click();

        //Verifying the HomePage of Toys Inventory
        //body//h3
        Thread.sleep(2000);
        //Checking We are Logged in...
        WebElement titleToyElement = driver.findElement(By.xpath("//body//h3"));
        String actualResult = titleToyElement.getText();
        String expectedResult = "Toy Inventory Processing Table";
        Assert.assertEquals(expectedResult, actualResult);

        //Verifying First Row of the Toys Table
        String[] toyRowValues= {
                "1", "Barbie Doll", "Doll New", "10", "9", "1","180.0","1620.0"
        };
        for(int i = 1; i<=8;i++){
            Thread.sleep(1000);
            WebElement rowElement = driver.findElement(By.xpath("//body[1]/div[1]/table[1]/tbody[1]/tr[1]/td[" + i +"]"));
            Assert.assertEquals(toyRowValues[i-1], rowElement.getText());
        }

        Thread.sleep(2000);

        //Now Going back to the HomePage and Testing Games Inventory
        w.until(ExpectedConditions.presenceOfElementLocated (By.xpath("//body[1]/div[3]/a[1]")));
        WebElement goBackElement = driver.findElement(By.xpath("//body[1]/div[3]/a[1]"));
        goBackElement.click();

        Thread.sleep(2000);
        //Waiting for the Toys and Games Button
        w.until(ExpectedConditions.presenceOfElementLocated (By.xpath("//body[1]/form[1]/button[2]")));
        WebElement gamesInventoryElement = driver.findElement(By.xpath("//body[1]/form[1]/button[2]"));
        gamesInventoryElement.click();

        //Verifying First Row of the Toys Table
        String[] gameRowValues= {
                "1", "Warhammer", "Tempest of Souls", "100", "50", "50","100.0","5000.0"
        };
        for(int i = 1; i<=8;i++){
            Thread.sleep(1000);
            WebElement rowElement = driver.findElement(By.xpath("//body[1]/div[1]/table[1]/tbody[1]/tr[1]/td[" + i +"]"));
            Assert.assertEquals(gameRowValues[i-1], rowElement.getText());
        }

        Thread.sleep(3000);
        driver.quit();

    }
}
