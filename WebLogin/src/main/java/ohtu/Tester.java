package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/home/aki/Tools/chromedriver");
        WebDriver driver = new ChromeDriver();
        Random rng = new Random();

        driver.get("http://localhost:4567");

        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        element = driver.findElement(By.name("username"));
        element.sendKeys("sauli" + rng.nextInt(100000));
        element = driver.findElement(By.name("password"));
        element.sendKeys("verysafe");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("verysafe");
        element = driver.findElement(By.name("signup"));
        element.submit();
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        element = driver.findElement(By.linkText("logout"));
        element.click();

        driver.quit();
    }

    private static void sleep(int n) {
        try {
            Thread.sleep(n * 100);
        } catch (Exception e) {
        }
    }
}
