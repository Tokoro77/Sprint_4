package ru.yandex.praktikum.scooter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

//Page Object для страницы оформления заказа Яндекс Самокат
//Содержит локаторы и методы для взаимодействия с формой заказа

public class OrderPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // === ЛОКАТОРЫ ДЛЯ ПЕРВОЙ СТРАНИЦЫ ФОРМЫ ЗАКАЗА ===
    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private final By lastNameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroField = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    // === ЛОКАТОРЫ ДЛЯ ВТОРОЙ СТРАНИЦЫ ФОРМЫ ЗАКАЗА ===
    private final By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodField = By.className("Dropdown-placeholder");
    private final By rentalPeriodOption = By.xpath(".//div[@class='Dropdown-option']");
    private final By blackColorCheckbox = By.id("black");
    private final By greyColorCheckbox = By.id("grey");
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM') and text()='Заказать']");

    // === ЛОКАТОРЫ ДЛЯ ПОДТВЕРЖДЕНИЯ ЗАКАЗА ===
    private final By confirmOrderButton = By.xpath(".//button[text()='Да']");
    private final By successMessage = By.xpath("//div[contains(@class, 'Order_ModalHeader') and contains(text(), 'Заказ оформлен')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // === МЕТОДЫ ДЛЯ РАБОТЫ С ПЕРВОЙ СТРАНИЦЕЙ ФОРМЫ ===
    public void fillFirstOrderPage(String firstName, String lastName, String address, String metroStation, String phone) {
        driver.findElement(nameField).sendKeys(firstName);
        driver.findElement(lastNameField).sendKeys(lastName);
        driver.findElement(addressField).sendKeys(address);

        WebElement metro = driver.findElement(metroField);
        metro.click();
        metro.sendKeys(metroStation);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + metroStation + "']"))).click();

        driver.findElement(phoneField).sendKeys(phone);
        driver.findElement(nextButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(dateField));
    }

    // === МЕТОДЫ ДЛЯ РАБОТЫ СО ВТОРОЙ СТРАНИЦЕЙ ФОРМЫ ===
    public void fillSecondOrderPage(String date, String rentalPeriod, String color, String comment) {
        WebElement dateElement = driver.findElement(dateField);
        dateElement.sendKeys(date);
        dateElement.sendKeys(Keys.ENTER);

        driver.findElement(rentalPeriodField).click();
        wait.until(ExpectedConditions.elementToBeClickable(rentalPeriodOption));

        driver.findElements(rentalPeriodOption).stream()
                .filter(element -> element.getText().equals(rentalPeriod))
                .findFirst()
                .ifPresent(WebElement::click);

        if ("black".equals(color)) {
            driver.findElement(blackColorCheckbox).click();
        } else if ("grey".equals(color)) {
            driver.findElement(greyColorCheckbox).click();
        }

        driver.findElement(commentField).sendKeys(comment);

        WebElement orderBtn = wait.until(ExpectedConditions.elementToBeClickable(orderButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", orderBtn);
        wait.until(ExpectedConditions.visibilityOfElementLocated(confirmOrderButton));
    }

    // === МЕТОДЫ ДЛЯ ПОДТВЕРЖДЕНИЯ И ПРОВЕРКИ ЗАКАЗА ===
    public void confirmOrder() {
        driver.findElement(confirmOrderButton).click();
    }

    public boolean isSuccessMessageDisplayed() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            return driver.findElement(successMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}