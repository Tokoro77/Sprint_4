package ru.yandex.praktikum.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.config.WebDriverFactory;
import ru.yandex.praktikum.scooter.constants.HomeConstants;
import ru.yandex.praktikum.scooter.constants.OrderConstants;
import ru.yandex.praktikum.scooter.pages.HomePage;
import ru.yandex.praktikum.scooter.pages.OrderPage;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertTrue;

//Тест для проверки оформления заказа самоката
//Проверяет полный флоу заказа через разные кнопки и с разными данными

@RunWith(Parameterized.class)
public class OrderTest {
    private WebDriver driver;
    private HomePage homePage;
    private OrderPage orderPage;

    // Параметры для теста
    private final String orderButtonType;
    private final int dataSetIndex;

    public OrderTest(String orderButtonType, int dataSetIndex) {
        this.orderButtonType = orderButtonType;
        this.dataSetIndex = dataSetIndex;
    }

    // === ТЕСТОВЫЕ ДАННЫЕ ДЛЯ ЗАКАЗОВ ===
    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {"top", 0},
                {"bottom", 1}
        });
    }

    @Before
    public void setUp() {
        driver = WebDriverFactory.getDriver();
        homePage = new HomePage(driver);
        orderPage = new OrderPage(driver);
        driver.get(HomeConstants.getSiteUrl());
        homePage.acceptCookies();
    }

    @Test
    public void testOrderScooter() {
        if ("top".equals(orderButtonType)) {
            homePage.clickOrderButtonTop();
        } else {
            homePage.clickOrderButtonBottom();
        }

        orderPage.fillFirstOrderPage(
                OrderConstants.getFirstName(dataSetIndex),
                OrderConstants.getLastName(dataSetIndex),
                OrderConstants.getAddress(dataSetIndex),
                OrderConstants.getMetroStation(dataSetIndex),
                OrderConstants.getPhone(dataSetIndex)
        );

        orderPage.fillSecondOrderPage(
                OrderConstants.getDate(dataSetIndex),
                OrderConstants.getRentalPeriod(dataSetIndex),
                OrderConstants.getColor(dataSetIndex),
                OrderConstants.getComment(dataSetIndex)
        );

        orderPage.confirmOrder();
        assertTrue("Не отображается сообщение об успешном создании заказа",
                orderPage.isSuccessMessageDisplayed());
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}