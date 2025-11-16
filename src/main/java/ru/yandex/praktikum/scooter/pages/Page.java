package ru.yandex.praktikum.scooter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Page {
    private final WebDriver driver;

    // Локаторы для раздела "Вопросы о важном"
    private final By accordionSection = By.className("Home_FAQ__3uVm4");

    // Метод для клика по вопросу (по индексу от 0 до 7)
    private By getQuestionLocator(int index) {
        return By.id("accordion__heading-" + index);
    }

    // Метод для получения текста ответа (по индексу)
    private By getAnswerLocator(int index) {
        return By.id("accordion__panel-" + index);
    }

    public Page(WebDriver driver) {
        this.driver = driver;
    }

    // Клик по вопросу с определенным индексом
    public void clickQuestion(int index) {
        driver.findElement(getQuestionLocator(index)).click();
    }

    // Получение текста ответа
    public String getAnswerText(int index) {
        return driver.findElement(getAnswerLocator(index)).getText();
    }

    // Проверка, что ответ отображается
    public boolean isAnswerDisplayed(int index) {
        return driver.findElement(getAnswerLocator(index)).isDisplayed();
    }
}