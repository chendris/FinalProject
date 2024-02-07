import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class SearchTest {


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
    public void search(){

        //accept cookies
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement cookie = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='onetrust-accept-btn-handler']")));
        cookie.click();

        //close popup
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='emailReengagement_close_button']")));
        popup.click();

        //type in searchBar and press enter
        WebElement searchBar = driver.findElement(By.xpath("//input[@id='header-search-input']"));
        searchBar.sendKeys("tricou");
        searchBar.sendKeys(Keys.ENTER);

        List <WebElement> allNames = driver.findElements(By.xpath("//div[@class='athenaProductBlock_title']"));
        Assert.assertTrue(allNames.get(0).getText().contains("Tricou"));
        driver.quit();


    }

}
