package ru.yandex.praktikum.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.config.WebDriverFactory;
import ru.yandex.praktikum.scooter.constants.HomePage;
import ru.yandex.praktikum.scooter.constants.OrderPage;
import ru.yandex.praktikum.scooter.pages.Home;
import ru.yandex.praktikum.scooter.pages.Order;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private Home homePage;
    private Order orderPage;
    private final String orderButtonType;
    private final int dataSetIndex;

    public OrderTest(String orderButtonType, int dataSetIndex) {
        this.orderButtonType = orderButtonType;
        this.dataSetIndex = dataSetIndex;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"top", 0}, {"top", 1}, {"bottom", 0}, {"bottom", 1}
        });
    }

    @Before
    public void setUp() {
        driver = WebDriverFactory.getDriver("chrome");
        homePage = new Home(driver);
        orderPage = new Order(driver);
        driver.get(HomePage.getSiteUrl());
        homePage.acceptCookies();
    }

    @Test
    public void testOrderScooter() {
        if ("top".equals(orderButtonType)) homePage.clickOrderButtonTop();
        else homePage.clickOrderButtonBottom();

        orderPage.fillFirstOrderPage(
                OrderPage.getFirstName(dataSetIndex),
                OrderPage.getLastName(dataSetIndex),
                OrderPage.getAddress(dataSetIndex),
                OrderPage.getMetroStation(dataSetIndex),
                OrderPage.getPhone(dataSetIndex)
        );

        orderPage.fillSecondOrderPage(
                OrderPage.getDate(dataSetIndex),
                OrderPage.getRentalPeriod(dataSetIndex),
                OrderPage.getColor(dataSetIndex),
                OrderPage.getComment(dataSetIndex)
        );

        orderPage.confirmOrder();
        assertTrue("Заказ не создан", orderPage.isSuccessMessageDisplayed());
    }

    @After
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}