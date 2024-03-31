package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.security.SecureRandom;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class MainPage {
    //находим поле поиска
    public SelenideElement searchFeild = $("[id=searchInput]");
    public SelenideElement suggestions = $(".suggestions");
    //находим коллекцию результатов
    public ElementsCollection suggestionsResults = $$(".suggestions-result");
    //находим кнопку поиска (лупу)
    public SelenideElement searchButton = $("[id=searchButton]");

}


