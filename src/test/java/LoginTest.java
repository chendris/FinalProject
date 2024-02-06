import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest {

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
    public void login(){
        //accept cookies
        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement cookie = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@id='onetrust-accept-btn-handler']")));
        cookie.click();

        //close popup
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='emailReengagement_close_button']")));
        popup.click();

        //click pe butonul de cont
        WebElement contButton = driver.findElement(By.xpath("//span[.='Cont']"));
        contButton.click();

        //inctroducere e-mail
        WebElement email = driver.findElement(By.xpath("//input[@label='Adresă de e-mail']"));
        email.sendKeys("calinandrei1104@yahoo.com");

        //introducere parola
        WebElement parola = driver.findElement(By.xpath("//input[@label='Parolă']"));
        parola.sendKeys("parola1234");

        //click pe butonul conectare
        WebElement conectareButton = driver.findElement(By.xpath("//button[@aria-label='Conectare la cont']"));
        conectareButton.click();

        //verificare logare cu succes
        WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[.='Pagină principală cont']")));
        Assert.assertTrue(success.getText().contains("Pagină principală cont"),"My account page not displayed");

        driver.quit();
    }

}
