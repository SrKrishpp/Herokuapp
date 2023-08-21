
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;


public class HeroBasicAuth {
    private WebDriver driver= new ChromeDriver();

    @Test
    public void basicAuth()
    {
        driver.get("http://the-internet.herokuapp.com/basic_auth");
        driver.get("http://admin:admin@the-internet.herokuapp.com/basic_auth/");
        String msg = driver.findElement(By.xpath("//p[contains(text(),'Congratulations')]")).getText();
        assertTrue(msg.contains("Congratulations!"));
        driver.quit();
    }
}
