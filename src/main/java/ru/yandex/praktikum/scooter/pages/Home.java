package ru.yandex.praktikum.scooter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Home {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы
    private final By cookieButton = By.id("rcc-confirm-button");
    private final By orderButtonTop = By.xpath("//button[contains(@class, 'Button_Button__ra12g') and not(contains(@class, 'Button_UltraBig__UU3Lp'))]");
    private final By orderButtonBottom = By.xpath("//button[contains(@class, 'Button_Button__ra12g') and contains(@class, 'Button_UltraBig__UU3Lp')]");
    private final By questionsSection = By.className("Home_FAQ__3uVm4");

    public Home(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // методы для работы с формой
    public void acceptCookies() {
        driver.findElement(cookieButton).click();
    }

    public void scrollToQuestions() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(questionsSection));
    }

    public String clickQuestionAndGetAnswer(int index) {
        By question = By.id("accordion__heading-" + index);
        By answer = By.id("accordion__panel-" + index);

        WebElement questionElement = driver.findElement(question);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", questionElement);
        questionElement.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(answer));
        return driver.findElement(answer).getText();
    }

    public void clickOrderButtonTop() {
        driver.findElement(orderButtonTop).click();
    }

    public void clickOrderButtonBottom() {
        WebElement button = driver.findElement(orderButtonBottom);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", button);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
    }
}