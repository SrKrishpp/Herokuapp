
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

public class CheckBox extends Haukhome {
    @Test
    public void testCheckBox() throws InterruptedException{
        driver.get("https://the-internet.herokuapp.com/");
        driver.findElement(By.xpath("//a[contains(text(),'Checkboxes')]")).click();
        List<WebElement> checkElements = driver.findElements(By.cssSelector("#checkboxes input[type='checkbox']"));
        System.out.println("no of checkboxes are : " + checkElements.size());
        for (WebElement checkbox : checkElements) {
            System.out.println("is this selected : " + checkbox.isSelected());
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }
    }
}
