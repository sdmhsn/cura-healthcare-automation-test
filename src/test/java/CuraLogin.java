import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class CuraLogin {
    WebDriver driver;

    @Test
    public void loginTest() {
        // open browser
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://katalon-demo-cura.herokuapp.com/profile.php#login");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // input username and password
        wait.until((ExpectedConditions.visibilityOfElementLocated(By.id("txt-username"))));
        driver.findElement(By.name("username")).sendKeys("John Doe");
        driver.findElement(By.xpath("//input[@id='txt-password']")).sendKeys("ThisIsNotAPassword");
        driver.findElement(By.xpath("//button[@id='btn-login']")).click();

        // verify success login
        wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(.,'Book Appointment')]"))));
        String txtSecureAreaActual = driver.findElement(By.xpath("//button[contains(.,'Book Appointment')]")).getText();
        String txtSecureAreaExpected = "Book Appointment";

        Assert.assertEquals(txtSecureAreaActual, txtSecureAreaExpected);

        // close browser
        driver.quit();
    }
}
