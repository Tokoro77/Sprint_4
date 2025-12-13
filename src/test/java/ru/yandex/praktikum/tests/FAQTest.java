package ru.yandex.praktikum.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.config.WebDriverFactory;
import ru.yandex.praktikum.scooter.constants.HomeConstants;
import ru.yandex.praktikum.scooter.pages.HomePage;
import static org.junit.Assert.assertEquals;

//Тест для проверки раздела "Вопросы о важном" (FAQ)
//Проверяет корректность отображения ответов при клике на вопросы
@RunWith(Parameterized.class)
public class FAQTest {
    private WebDriver driver;
    private HomePage homePage;

    private final int questionIndex;
    private final String expectedAnswer;
    private final String questionName;

    public FAQTest(int questionIndex, String expectedAnswer, String questionName) {
        this.questionIndex = questionIndex;
        this.expectedAnswer = expectedAnswer;
        this.questionName = questionName;
    }

    // === ТЕСТОВЫЕ ДАННЫЕ ДЛЯ FAQ ===
    @Parameterized.Parameters(name = "Вопрос {0}: {2}")
    public static Object[][] data() {
        return new Object[][] {
                {0, "Сутки — 400 рублей. Оплата курьеру — наличными или картой.", "Сколько это стоит? И как оплатить?"},
                {1, "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.", "Хочу сразу несколько самокатов! Так можно?"},
                {2, "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.", "Как рассчитывается время аренды?"},
                {3, "Только начиная с завтрашнего дня. Но скоро станем расторопнее.", "Можно ли заказать самокат прямо на сегодня?"},
                {4, "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.", "Можно ли продлить заказ или вернуть самокат раньше?"},
                {5, "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.", "Вы привозите зарядку вместе с самокатом?"},
                {6, "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.", "Можно ли отменить заказ?"},
                {7, "Да, обязательно. Всем самокатов! И Москве, и Московской области.", "Я живу за МКАДом, привезёте?"}
        };
    }

    @Before
    public void setUp() {
        driver = WebDriverFactory.getDriver();
        homePage = new HomePage(driver);
        driver.get(HomeConstants.getSiteUrl());
        homePage.acceptCookies();
        homePage.scrollToQuestions();
    }

    @Test
    public void checkFAQAnswer() {
        homePage.clickQuestion(questionIndex);
        String actualAnswer = homePage.getAnswerText(questionIndex);
        assertEquals("Текст ответа на вопрос '" + questionName + "' не совпадает",
                expectedAnswer, actualAnswer);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}