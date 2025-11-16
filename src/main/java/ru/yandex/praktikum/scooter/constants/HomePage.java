package ru.yandex.praktikum.scooter.constants;

import org.openqa.selenium.By;

public class HomePage {
    // URL
    public static final String SITE_URL = "https://qa-scooter.praktikum-services.ru/";

    // ЛОКАТОРЫ ДЛЯ КНОПОК ЗАКАЗА
    public static final By ORDER_BUTTON_TOP = By.xpath("//button[contains(@class, 'Button_Button__ra12g') and not(contains(@class, 'Button_UltraBig__UU3Lp')) and text()='Заказать']");
    public static final By ORDER_BUTTON_BOTTOM = By.xpath("//button[contains(@class, 'Button_Button__ra12g') and contains(@class, 'Button_UltraBig__UU3Lp') and text()='Заказать']");

    // Локаторы для вопросов
    public static final By QUESTION_0 = By.id("accordion__heading-0");
    public static final By QUESTION_1 = By.id("accordion__heading-1");
    public static final By QUESTION_2 = By.id("accordion__heading-2");
    public static final By QUESTION_3 = By.id("accordion__heading-3");
    public static final By QUESTION_4 = By.id("accordion__heading-4");
    public static final By QUESTION_5 = By.id("accordion__heading-5");
    public static final By QUESTION_6 = By.id("accordion__heading-6");
    public static final By QUESTION_7 = By.id("accordion__heading-7");

    // Локаторы для ответов
    public static final By ANSWER_0 = By.id("accordion__panel-0");
    public static final By ANSWER_1 = By.id("accordion__panel-1");
    public static final By ANSWER_2 = By.id("accordion__panel-2");
    public static final By ANSWER_3 = By.id("accordion__panel-3");
    public static final By ANSWER_4 = By.id("accordion__panel-4");
    public static final By ANSWER_5 = By.id("accordion__panel-5");
    public static final By ANSWER_6 = By.id("accordion__panel-6");
    public static final By ANSWER_7 = By.id("accordion__panel-7");

    // Локаторы для проверки загрузки
    public static final By ITEM_ANSWER_0 = By.xpath(".//div[@id='accordion__panel-0']/p");
    public static final By ITEM_ANSWER_1 = By.xpath(".//div[@id='accordion__panel-1']/p");
    public static final By ITEM_ANSWER_2 = By.xpath(".//div[@id='accordion__panel-2']/p");
    public static final By ITEM_ANSWER_3 = By.xpath(".//div[@id='accordion__panel-3']/p");
    public static final By ITEM_ANSWER_4 = By.xpath(".//div[@id='accordion__panel-4']/p");
    public static final By ITEM_ANSWER_5 = By.xpath(".//div[@id='accordion__panel-5']/p");
    public static final By ITEM_ANSWER_6 = By.xpath(".//div[@id='accordion__panel-6']/p");
    public static final By ITEM_ANSWER_7 = By.xpath(".//div[@id='accordion__panel-7']/p");

    // Тексты ответов
    public static final String TEXT_ANSWER_0 = "Сутки — 400 рублей. Оплата курьеру — наличными или картой.";
    public static final String TEXT_ANSWER_1 = "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.";
    public static final String TEXT_ANSWER_2 = "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.";
    public static final String TEXT_ANSWER_3 = "Только начиная с завтрашнего дня. Но скоро станем расторопнее.";
    public static final String TEXT_ANSWER_4 = "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.";
    public static final String TEXT_ANSWER_5 = "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.";
    public static final String TEXT_ANSWER_6 = "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.";
    public static final String TEXT_ANSWER_7 = "Да, обязательно. Всем самокатов! И Москве, и Московской области.";
}