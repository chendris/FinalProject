import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class SortTest {

    WebDriver driver;
    String url="https://www.myprotein.ro/";
    @BeforeTest
    public void before(){
        //acceseaza pagina
        driver = new ChromeDriver();
        driver.get(url);
        driver.manage().window().maximize();

    }

    @Test
    public void sort() throws InterruptedException {
        //accept cookies
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement cookie = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='onetrust-accept-btn-handler']")));
        cookie.click();

        //close popup
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='emailReengagement_close_button']")));
        popup.click();

        //Click on Proteine Button
        WebElement proteineButton = driver.findElement(By.xpath("//a[@data-cs-override-id='Proteine']"));
        proteineButton.click();

        //select descending sort
        WebElement sortareDupa = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//select[@aria-label='Sortare după:']")));
        Select dropdown = new Select(sortareDupa);
        dropdown.selectByValue("priceDescending");

        try {
            // Sleep for 1 seconds
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //comparing first 3 elements
        List <WebElement> allPrices = driver.findElements(By.xpath("//div[@class='athenaProductBlock_priceBlock']"));
        int price1 = 0;
        int price2 = 0;
        Boolean sortSuccess=false;




        for(int i=0; i<3;i++){
            sortSuccess=false;
            if (allPrices.get(i).getText().matches("^\\d.*")) {
                price1 = Integer.parseInt(allPrices.get(i).getText().substring(0,allPrices.get(i).getText().length()-6 ));

            }
            else {
                String substring = allPrices.get(i).getText().substring(6);
                price1 =Integer.parseInt(substring.substring(0,substring.length()-6));
            }
            if(price2 != 0 && price1 <= price2) sortSuccess = true;

            price2 = price1;

        }
        Assert.assertTrue(sortSuccess);
        driver.quit();
    }
}
