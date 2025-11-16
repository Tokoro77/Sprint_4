package ru.yandex.praktikum.scooter.constants;

import org.openqa.selenium.By;

public class OrderPage {
    // Данные для заказов
    public static final String[] FIRST_NAMES = {"Иван", "Мария"};
    public static final String[] LAST_NAMES = {"Петров", "Сидорова"};
    public static final String[] ADDRESSES = {"ул. Ленина, д. 1", "пр. Мира, д. 25"};
    public static final String[] METRO_STATIONS = {"Сокольники", "Черкизовская"};
    public static final String[] PHONES = {"+79991234567", "+79997654321"};
    public static final String[] DATES = {"15.12.2024", "20.12.2024"};
    public static final String[] RENTAL_PERIODS = {"сутки", "двое суток"};
    public static final String[] COLORS = {"black", "grey"};
    public static final String[] COMMENTS = {"Позвонить за час", "Оставить у двери"};

    // Локаторы для первой страницы заказа
    public static final By NAME_FIELD = By.xpath(".//input[@placeholder='* Имя']");
    public static final By LAST_NAME_FIELD = By.xpath(".//input[@placeholder='* Фамилия']");
    public static final By ADDRESS_FIELD = By.xpath(".//input[@placeholder='* Адрес: куда привезти заказ']");
    public static final By METRO_FIELD = By.xpath(".//input[@placeholder='* Станция метро']");
    public static final By PHONE_FIELD = By.xpath(".//input[@placeholder='* Телефон: на него позвонит курьер']");
    public static final By NEXT_BUTTON = By.xpath(".//button[text()='Далее']");

    // Локаторы для второй страницы заказа
    public static final By DATE_FIELD = By.xpath(".//input[@placeholder='* Когда привезти самокат']");
    public static final By RENTAL_PERIOD_FIELD = By.className("Dropdown-placeholder");
    public static final By RENTAL_PERIOD_OPTION = By.xpath(".//div[@class='Dropdown-option']");
    public static final By BLACK_COLOR_CHECKBOX = By.id("black");
    public static final By GREY_COLOR_CHECKBOX = By.id("grey");
    public static final By COMMENT_FIELD = By.xpath(".//input[@placeholder='Комментарий для курьера']");
    public static final By ORDER_BUTTON = By.xpath("//button[contains(@class, 'Button_Button__ra12g') and contains(@class, 'Button_Middle__1CSJM') and text()='Заказать']");

    // Локаторы для подтверждения заказа
    public static final By CONFIRM_ORDER_BUTTON = By.xpath(".//button[text()='Да']");
    public static final By SUCCESS_MESSAGE = By.xpath(".//div[contains(@class, 'Order_ModalHeader')]");
}