package ru.yandex.praktikum.scooter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.scooter.constants.HomePage;

import java.time.Duration;

public class Home {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы
    private final By cookieButton = By.id("rcc-confirm-button");
    private final By questionsSection = By.className("Home_FAQ__3uVm4");

    public Home(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public Home waitForLoadHomePage() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(HomePage.ORDER_BUTTON_TOP));
        return this;
    }

    public Home acceptCookies() {
        driver.findElement(cookieButton).click();
        return this;
    }

    public Home scrollToQuestions() {
        WebElement element = driver.findElement(questionsSection);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        return this;
    }

    public Home clickQuestion(By questionLocator) {
        WebElement question = driver.findElement(questionLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", question);
        question.click();
        return this;
    }

    public Home waitLoadAfterClickQuestion(By answerLocator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
        return this;
    }

    public void clickOrderButtonTop() {
        WebElement element = driver.findElement(HomePage.ORDER_BUTTON_TOP);
        element.click();
    }

    public void clickOrderButtonBottom() {
        WebElement element = driver.findElement(HomePage.ORDER_BUTTON_BOTTOM);

        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center', behavior: 'smooth'});", element);

        wait.until(ExpectedConditions.elementToBeClickable(element));

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }
}