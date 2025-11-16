package ru.yandex.praktikum.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.yandex.praktikum.config.WebDriverFactory;
import ru.yandex.praktikum.scooter.constants.HomePage;
import ru.yandex.praktikum.scooter.pages.Home;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class FAQtest {
    private WebDriver driver;
    private Home homePage;
    private final int questionIndex;

    public FAQtest(int questionIndex) {
        this.questionIndex = questionIndex;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                {0}, {1}, {2}, {3}, {4}, {5}, {6}, {7}
        });
    }

    @Before
    public void setUp() {
        // Запуск в Chrome вместо Firefox
        //driver = WebDriverFactory.getDriver("firefox");
        driver = WebDriverFactory.getDriver("chrome");

        // Переход на сайт
        driver.get(HomePage.SITE_URL);

        // Инициализация Page Object
        homePage = new Home(driver);

        // Ожидание загрузки и принятие куки
        homePage.waitForLoadHomePage().acceptCookies();
    }

    @Test
    public void testQuestionAnswer() {
        // Скроллим к разделу с вопросами
        homePage.scrollToQuestions();

        // Получаем локаторы для текущего вопроса и ответа
        By questionLocator = getQuestionLocator(questionIndex);
        By answerLocator = getAnswerLocator(questionIndex);
        String expectedAnswer = getExpectedAnswer(questionIndex);

        // Кликаем на вопрос
        homePage.clickQuestion(questionLocator);

        // Ждем загрузки ответа
        homePage.waitLoadAfterClickQuestion(answerLocator);

        // Получаем фактический текст ответа
        String actualAnswer = driver.findElement(answerLocator).getText();

        // Проверяем, что ответ соответствует ожидаемому
        assertEquals("Текст ответа на вопрос " + (questionIndex + 1) + " не совпадает",
                expectedAnswer, actualAnswer);
    }

    // Вспомогательные методы для получения локаторов и текстов
    private By getQuestionLocator(int index) {
        switch (index) {
            case 0: return HomePage.QUESTION_0;
            case 1: return HomePage.QUESTION_1;
            case 2: return HomePage.QUESTION_2;
            case 3: return HomePage.QUESTION_3;
            case 4: return HomePage.QUESTION_4;
            case 5: return HomePage.QUESTION_5;
            case 6: return HomePage.QUESTION_6;
            case 7: return HomePage.QUESTION_7;
            default: throw new IllegalArgumentException("Неверный индекс вопроса: " + index);
        }
    }

    private By getAnswerLocator(int index) {
        switch (index) {
            case 0: return HomePage.ANSWER_0;
            case 1: return HomePage.ANSWER_1;
            case 2: return HomePage.ANSWER_2;
            case 3: return HomePage.ANSWER_3;
            case 4: return HomePage.ANSWER_4;
            case 5: return HomePage.ANSWER_5;
            case 6: return HomePage.ANSWER_6;
            case 7: return HomePage.ANSWER_7;
            default: throw new IllegalArgumentException("Неверный индекс ответа: " + index);
        }
    }

    private String getExpectedAnswer(int index) {
        switch (index) {
            case 0: return HomePage.TEXT_ANSWER_0;
            case 1: return HomePage.TEXT_ANSWER_1;
            case 2: return HomePage.TEXT_ANSWER_2;
            case 3: return HomePage.TEXT_ANSWER_3;
            case 4: return HomePage.TEXT_ANSWER_4;
            case 5: return HomePage.TEXT_ANSWER_5;
            case 6: return HomePage.TEXT_ANSWER_6;
            case 7: return HomePage.TEXT_ANSWER_7;
            default: throw new IllegalArgumentException("Неверный индекс ответа: " + index);
        }
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}