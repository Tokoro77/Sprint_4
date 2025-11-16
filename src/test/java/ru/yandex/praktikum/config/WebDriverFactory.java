package ru.yandex.praktikum.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class WebDriverFactory {
    public static WebDriver getDriver(String browserType) {
        WebDriver driver;

        switch (browserType.toLowerCase()) {
            //case "firefox":
            //  WebDriverManager.firefoxdriver().setup();
            // driver = new FirefoxDriver();
            // break;
            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
                break;
        }

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }
}