package ru.yandex.praktikum.scooter.constants;

 //Класс для хранения констант главной страницы
 //Содержит только статические данные, без локаторов и методов работы с элементами

public class HomeConstants {
    // URL главной страницы сервиса Яндекс Самокат
    private static final String SITE_URL = "https://qa-scooter.praktikum-services.ru/";
    public static String getSiteUrl() {
        return SITE_URL;
    }
}