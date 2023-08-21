import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class BrokenImg {

    private int brokenImageCount = 0;

    @Test
    public void test() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://the-internet.herokuapp.com/");
        driver.findElement(By.xpath("//a[contains(text(),'Broken Images')]")).click();
        List<WebElement> imgElements = driver.findElements(By.cssSelector("div.example img"));

        for (WebElement image : imgElements) {
            if (!isImageValid(image)) {
                brokenImageCount++;
            }
        }

        System.out.println("No. of broken images: " + brokenImageCount);

        driver.quit();
    }

    private boolean isImageValid(WebElement image) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest getRequest = HttpRequest.newBuilder()
                .uri(URI.create(image.getAttribute("src")))
                .build();

        try {
            HttpResponse<Void> response = client.send(getRequest, HttpResponse.BodyHandlers.discarding());
            return response.statusCode() == 200;
        } catch (IOException | InterruptedException e) {
            return false;
        }
    }
}
