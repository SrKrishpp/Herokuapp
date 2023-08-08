import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;

public class Haukhome {
    private WebDriver driver;
    private WebDriverWait wait;
    Duration timeout = Duration.ofSeconds(10);
    @BeforeClass
    public void setUp() {
        WebDriverManager.chromedriver().setup(); // Use WebDriverManager to set up ChromeDriver
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, timeout);
    }
    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    @Test
    public void testAddRemoveElements() throws InterruptedException {
        HomePage homePage = new HomePage(driver);
        homePage.goToAddRemoveElements();
        homePage.validateAddElements(3);
        homePage.validateRemoveElements();
    }
}
class HomePage{
    private final WebDriver driver;
    private final WebDriverWait wait;
    Duration timeout = Duration.ofSeconds(10);

    public HomePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, timeout);
    }
    public void goToAddRemoveElements() {
        driver.get("https://the-internet.herokuapp.com/");
        WebElement pageLink = driver.findElement(By.xpath("//a[contains(text(),'Add/Remove Elements')]"));
        pageLink.click();
    }
    public void validateAddElements(int noOfClicks) {
        WebElement addElementBtn = driver.findElement(By.xpath("//button[contains(text(),'Add Element')]"));
        for (int i = 0; i < noOfClicks; i++) {
            addElementBtn.click();
        }
        List<WebElement> elements = driver.findElements(By.xpath("//div[@id='elements']/button"));
        Assert.assertEquals(noOfClicks, elements.size());
    }
    public void validateRemoveElements() throws InterruptedException {

            List<WebElement> removeElementBtn = driver.findElements(By.cssSelector("button.added-manually"));
            for (WebElement webElement : removeElementBtn) {
                webElement.click();
                wait.until(ExpectedConditions.stalenessOf(webElement));
            }

    }

}