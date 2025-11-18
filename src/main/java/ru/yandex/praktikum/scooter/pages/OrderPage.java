package ru.yandex.praktikum.scooter.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.praktikum.scooter.constants.OrderConstants;
import java.time.Duration;
import java.util.List;

//Page Object для страницы оформления заказа Яндекс Самокат
//Содержит локаторы и методы для взаимодействия с формой заказа
public class OrderPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // === локаторы для первой страницы формы заказа ===
    private final By nameField = By.xpath(".//input[@placeholder='* Имя']");
    private final By lastNameField = By.xpath(".//input[@placeholder='* Фамилия']");
    private final By addressField = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    private final By metroField = By.xpath(".//input[@placeholder='* Станция метро']");
    private final By phoneField = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath(".//button[text()='Далее']");

    // === локаторы для второй страницы формы заказа ===
    private final By dateField = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    private final By rentalPeriodField = By.className("Dropdown-placeholder");
    private final By rentalPeriodOption = By.xpath(".//div[@class='Dropdown-option']");
    private final By blackColorCheckbox = By.id("black");
    private final By greyColorCheckbox = By.id("grey");
    private final By commentField = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath("//button[contains(@class, 'Button_Middle__1CSJM') and text()='Заказать']");

    // === локаторы для подтверждения заказа ===
    private final By confirmOrderButton = By.xpath(".//button[text()='Да']");
    private final By successMessage = By.xpath("//div[contains(@class, 'Order_ModalHeader') and contains(text(), 'Заказ оформлен')]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // === методы для работы с первой страницей формы ===
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

    // === методы для работы со второй страницей формы ===
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

    // === методы для подтверждения и проверки заказа ===
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

    // === геттеры тестовых данных ===
    public String getFirstName(int index) {return OrderConstants.getTestData()[index][0];}
    public String getLastName(int index) {return OrderConstants.getTestData()[index][1];}
    public String getAddress(int index) {return OrderConstants.getTestData()[index][2];}
    public String getMetroStation(int index) {return OrderConstants.getTestData()[index][3];}
    public String getPhone(int index) {return OrderConstants.getTestData()[index][4];}
    public String getDate(int index) {return OrderConstants.getTestData()[index][5];}
    public String getRentalPeriod(int index) {return OrderConstants.getTestData()[index][6];}
    public String getColor(int index) {return OrderConstants.getTestData()[index][7];}
    public String getComment(int index) {return OrderConstants.getTestData()[index][8];}
}