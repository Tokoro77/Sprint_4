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
                {"top", 0},      // Верхняя кнопка с первым набором данных
                {"top", 1},      // Верхняя кнопка со вторым набором данных
                {"bottom", 0},   // Нижняя кнопка с первым набором данных
                {"bottom", 1}    // Нижняя кнопка со вторым набором данных
        });
    }

    @Before
    public void setUp() {
        // Запуск в CHROME вместо Firefox
        //driver = WebDriverFactory.getDriver("firefox");
        driver = WebDriverFactory.getDriver("chrome");

        // Переход на сайт
        driver.get(HomePage.SITE_URL);

        // Инициализация Page Objects
        homePage = new Home(driver);
        orderPage = new Order(driver);

        // Ожидание загрузки и принятие куки
        homePage.waitForLoadHomePage().acceptCookies();
    }

    @Test
    public void testOrderScooter() {
        // Нажимаем на кнопку заказа в зависимости от параметра
        if ("top".equals(orderButtonType)) {
            homePage.clickOrderButtonTop();
        } else {
            homePage.clickOrderButtonBottom();
        }

        // Заполняем первую страницу заказа
        orderPage.fillFirstOrderPage(
                OrderPage.FIRST_NAMES[dataSetIndex],
                OrderPage.LAST_NAMES[dataSetIndex],
                OrderPage.ADDRESSES[dataSetIndex],
                OrderPage.METRO_STATIONS[dataSetIndex],
                OrderPage.PHONES[dataSetIndex]
        );

        // Заполняем вторую страницу заказа
        orderPage.fillSecondOrderPage(
                OrderPage.DATES[dataSetIndex],
                OrderPage.RENTAL_PERIODS[dataSetIndex],
                OrderPage.COLORS[dataSetIndex],
                OrderPage.COMMENTS[dataSetIndex]
        );

        // Подтверждаем заказ
        orderPage.confirmOrder();

        // Проверяем успешное создание заказа
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