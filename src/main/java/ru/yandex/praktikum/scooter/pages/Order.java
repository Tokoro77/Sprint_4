package ru.yandex.praktikum.scooter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.scooter.constants.OrderPage;

import java.time.Duration;
import java.util.List;

public class Order {
    private final WebDriver driver;
    private final WebDriverWait wait;

    public Order(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void fillFirstOrderPage(String firstName, String lastName, String address, String metroStation, String phone) {
        // Заполняем поля
        driver.findElement(OrderPage.NAME_FIELD).sendKeys(firstName);
        driver.findElement(OrderPage.LAST_NAME_FIELD).sendKeys(lastName);
        driver.findElement(OrderPage.ADDRESS_FIELD).sendKeys(address);

        // Выбираем станцию метро
        WebElement metroField = driver.findElement(OrderPage.METRO_FIELD);
        metroField.click();
        metroField.sendKeys(metroStation);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[text()='" + metroStation + "']"))).click();

        driver.findElement(OrderPage.PHONE_FIELD).sendKeys(phone);

        // Нажимаем Далее
        driver.findElement(OrderPage.NEXT_BUTTON).click();

        // Ждем загрузки второй страницы
        wait.until(ExpectedConditions.visibilityOfElementLocated(OrderPage.DATE_FIELD));
    }

    public void fillSecondOrderPage(String date, String rentalPeriod, String color, String comment) {
        // Заполняем дату
        WebElement dateField = driver.findElement(OrderPage.DATE_FIELD);
        dateField.sendKeys(date);
        dateField.sendKeys(Keys.ENTER);

        // Выбираем срок аренды
        driver.findElement(OrderPage.RENTAL_PERIOD_FIELD).click();
        wait.until(ExpectedConditions.elementToBeClickable(OrderPage.RENTAL_PERIOD_OPTION));
        driver.findElements(OrderPage.RENTAL_PERIOD_OPTION).stream()
                .filter(element -> element.getText().equals(rentalPeriod))
                .findFirst()
                .ifPresent(WebElement::click);

        // Выбираем цвет
        if ("black".equals(color)) {
            driver.findElement(OrderPage.BLACK_COLOR_CHECKBOX).click();
        } else if ("grey".equals(color)) {
            driver.findElement(OrderPage.GREY_COLOR_CHECKBOX).click();
        }

        // Заполняем комментарий
        driver.findElement(OrderPage.COMMENT_FIELD).sendKeys(comment);

        // КЛИК ПО КНОПКЕ "ЗАКАЗАТЬ"
        driver.findElement(OrderPage.ORDER_BUTTON).click();

        // Ждем появления окна подтверждения
        wait.until(ExpectedConditions.visibilityOfElementLocated(OrderPage.CONFIRM_ORDER_BUTTON));
    }

    public void confirmOrder() {
        // Нажимаем кнопку "Да" в модальном окне
        driver.findElement(OrderPage.CONFIRM_ORDER_BUTTON).click();

        // Ждем появления сообщения об успехе
        wait.until(ExpectedConditions.visibilityOfElementLocated(OrderPage.SUCCESS_MESSAGE));
    }

    public boolean isSuccessMessageDisplayed() {
        try {
            return driver.findElement(OrderPage.SUCCESS_MESSAGE).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}