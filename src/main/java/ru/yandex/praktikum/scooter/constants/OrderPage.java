package ru.yandex.praktikum.scooter.constants;

public class OrderPage {
    private static final String[][] TEST_DATA = {
            // Имя, Фамилия, Адрес, Станция метро, Телефон, Дата, Период, Цвет, Комментарий
            {"Иван", "Петров", "ул. Ленина, д. 1", "Сокольники", "+79991234567", "15.12.2024", "сутки", "black", "Позвонить за час"},
            {"Мария", "Сидорова", "пр. Мира, д. 25", "Черкизовская", "+79997654321", "20.12.2024", "двое суток", "grey", "Оставить у двери"}
    };

    public static String getFirstName(int index) { return TEST_DATA[index][0]; }
    public static String getLastName(int index) { return TEST_DATA[index][1]; }
    public static String getAddress(int index) { return TEST_DATA[index][2]; }
    public static String getMetroStation(int index) { return TEST_DATA[index][3]; }
    public static String getPhone(int index) { return TEST_DATA[index][4]; }
    public static String getDate(int index) { return TEST_DATA[index][5]; }
    public static String getRentalPeriod(int index) { return TEST_DATA[index][6]; }
    public static String getColor(int index) { return TEST_DATA[index][7]; }
    public static String getComment(int index) { return TEST_DATA[index][8]; }
}