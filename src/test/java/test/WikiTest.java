package test;

import data.DataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import page.MainPage;
import page.SearchResult;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.open;


public class WikiTest {

    MainPage mainPage = new MainPage();
    DataGenerator dataGenerator = new DataGenerator();

    //Запуск страницы перед каждым тестом
    @BeforeEach
    void setup() {
        open("https://ru.wikipedia.org/wiki/%D0%97%D0%B0%D0%B3%D0%BB%D0%B0%D0%B2%D0%BD%D0%B0%D1%8F_%D1%81%D1%82%D1%80%D0%B0%D0%BD%D0%B8%D1%86%D0%B0",
                MainPage.class);
    }
    //поиск выделенного текста
    @Test
    void shouldBeBaldText() {
        //заполняем случайной буквой поле поиска
        mainPage.searchFeild.setValue(dataGenerator.generatingRandomAlphanumeric());
        // Ищем средь полей хотя бы одну выделенную букву по атрибуту
        mainPage.suggestionsResults.findBy(attribute(".highlight"));
    }

    // Переход саджеста по поисковой фразе на точную страницу
    @Test
    void shouldGoToExactlyPage() {
        // Вводится искомое слово
        mainPage.searchFeild.setValue("Контакт");
        // Ожидание, когда появятся саджесты
        mainPage.suggestions.shouldBe(visible, Duration.ofSeconds(10));
        //Клик по первому саджесту
        mainPage.suggestions.find(".mw-searchSuggest-link").click();
        var searchResult = new SearchResult();
        //Проверка на идентичность слов
        searchResult.firstHead.shouldHave(text("Контакт"));
    }

    //Переход по кнопке поиска на страницу первого саджеста
    @Test
    void shouldGoFirstClue() {
        // Вводится искомое слово
        mainPage.searchFeild.setValue("Контакт");
        // Ожидание, когда появятся саджесты
        mainPage.suggestions.shouldBe(visible, Duration.ofSeconds(10));
        //Клик по кнопке поиска
        mainPage.searchButton.click();
        var searchResult = new SearchResult();
        //Проверка на идентичность слов
        searchResult.firstHead.shouldHave(text("Контакт"));
    }

    //Переход на поиск с вхождением искомого слова
    @Test
    void shouldGoPageWithoutClue() {
        // Вводится искомое слово
        mainPage.searchFeild.setValue("Контакттттт");
        // Ожидание, когда появятся саджесты
        mainPage.suggestions.shouldBe(visible, Duration.ofSeconds(10));
        //Переход на следующую страницу нажатием ENTER
        mainPage.searchFeild.sendKeys(Keys.ENTER);
        var searchResult = new SearchResult();
        //Проверка на идентичность слов
        searchResult.searchText
                .shouldBe(visible, Duration.ofSeconds(10))
                .find("input[value=\"Контакттттт\"]");
    }
    //Поиск подсказки и переход по ней на страницу.
    // Тест падает, подсказка не отображается во время тестов
    @Test
    void shouldGoPageOfSearch() {
        //клик по полю
        mainPage.searchFeild.click();
        //Печать искомого текста
        mainPage.searchFeild.sendKeys("Контакт");
        // Ожидание, когда появятся саджесты
        mainPage.suggestions.shouldBe(visible, Duration.ofSeconds(10));
        // Клик по искомому саджесту
        mainPage.suggestions.find(".suggestions-special").click();
        var searchResult = new SearchResult();
        searchResult.searchText
                .shouldBe(visible, Duration.ofSeconds(10))
                .find("input[value=\"Контакт\"]");

    }
}
