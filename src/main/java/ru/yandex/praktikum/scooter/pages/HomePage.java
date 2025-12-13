package ru.yandex.praktikum.scooter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

//Page Object для главной страницы Яндекс Самокат
//Содержит локаторы и методы для взаимодействия с элементами страницы

public class HomePage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // === ЛОКАТОРЫ ДЛЯ ОСНОВНЫХ ЭЛЕМЕНТОВ ===
    private final By cookieButton = By.id("rcc-confirm-button");
    private final By orderButtonTop = By.xpath("(//button[text()='Заказать'])[1]");
    private final By orderButtonBottom = By.xpath("(//button[text()='Заказать'])[2]");
    private final By questionsSection = By.className("Home_FAQ__3uVm4");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // === ВСПОМОГАТЕЛЬНЫЕ МЕТОДЫ ДЛЯ РАБОТЫ С FAQ ===

    private By getQuestionLocator(int index) {
        return By.id("accordion__heading-" + index);
    }

    private By getAnswerLocator(int index) {
        return By.id("accordion__panel-" + index);
    }

    // === МЕТОДЫ ДЛЯ РАБОТЫ С КУКИ И НАВИГАЦИЕЙ ===

    public void acceptCookies() {
        driver.findElement(cookieButton).click();
    }
    public void scrollToQuestions() {
        WebElement element = driver.findElement(questionsSection);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    // === МЕТОДЫ ДЛЯ РАБОТЫ С ВОПРОСАМИ И ОТВЕТАМИ ===

    public void clickQuestion(int index) {
        By questionLocator = getQuestionLocator(index);
        By answerLocator = getAnswerLocator(index);

        WebElement question = driver.findElement(questionLocator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", question);
        question.click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(answerLocator));
    }

    public String getAnswerText(int index) {
        return driver.findElement(getAnswerLocator(index)).getText();
    }

    // === МЕТОДЫ ДЛЯ РАБОТЫ С КНОПКАМИ ЗАКАЗА ===

    public void clickOrderButtonTop() {
        driver.findElement(orderButtonTop).click();
    }
    public void clickOrderButtonBottom() {
        WebElement button = driver.findElement(orderButtonBottom);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", button);
        button.click();
    }
}