import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class EbayAddToCartTest {
    public static void main(String[] args) {
        // path to the chromedriver executable
        System.setProperty("webdriver.chrome.driver", "C://Users//chromedriver.exe");
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--remote-allow-origins=*");

        // Initialize WebDriver
        WebDriver driver = new ChromeDriver();

        driver.get("https://www.ebay.com");
        driver.manage().window().maximize();
        // Search for 'book'
        WebElement searchBox = driver.findElement(By.id("gh-ac"));
        searchBox.sendKeys("book");
        driver.findElement(By.xpath("//span[text()='Search']")).click();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        //Click on the first book in the list
        driver.findElement(By.xpath("(//span[@role='heading'])[3]")).click();

        //Scroll down the page
        Actions actions = new Actions(driver);
        actions.sendKeys(Keys.PAGE_DOWN).perform();

        // In the item listing page, click on 'Add to cart'
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("atcBtn_btn_1")));
        addToCartButton.click();

        // Verify the cart has been updated and displays the number of items in the cart
        WebElement cartCount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='gh-cart__icon']")));
        String itemCount = cartCount.getText();
        System.out.println("Items in cart: " + itemCount);

//       Validate the cart count is updated
            if (!itemCount.equals("0")) {
                System.out.println("Test Passed: Item successfully added to cart.");
            } else {
                System.out.println("Test Failed: Item not added to cart.");
            }
        driver.close();
    }
}
