package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.time.Duration;

public class Driver {
    /*
        Driver claass'indan drigiver'i getDriver methodu ile kullaniyoruz
        sonradan projeye katilan insanlarin Driver class'indan obje olusturarak
        driver kullanmaya calismalarini engellemek icin

        Driver class'ini SINGLEON PATTERN ile duzenleyebiliriz
        Bunun icin Driver class'inin parametresiz consrustor'ini olusturup
        access modifier'ini private yapmamiz yeterli olur
     */

    //kimse bu class'dan obje uretemez
    private Driver(){

    }
    static WebDriver driver;

    static ChromeOptions ops = new ChromeOptions();

    public static WebDriver getDriver() {
        if (driver == null) {
            switch (ConfigReader.getProperty("browser")) {
                case "chrome":
                    ops.addArguments("--remote-allow-origins=*");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(ops);
                    break;
                case "safari":
                    WebDriverManager.safaridriver().setup();
                    driver = new SafariDriver();
                    break;

                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                    break;
                default:
                    ops.addArguments("--remote-allow-origins=*");
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver(ops);


            }

            driver.manage().window().maximize();
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        }
        return driver;
    }

    public static void closeDriver() {
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }

    public static void quitDriver() {
        if (driver != null) {
            driver.close();
            driver = null;
        }
    }
}