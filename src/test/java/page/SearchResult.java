package page;

import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class SearchResult {
    public SelenideElement firstHead = $("[id=firstHeading]");
    public SelenideElement searchText = $("[id=searchText]");

    SelenideElement redirection = $(".mw-redirectedfrom");

    //public SearchResult() {
    //  firstHead.shouldBe(visible, Duration.ofSeconds(10));
    // }
    public String extractionText() {
        firstHead.getText();
        searchText.getText();
        return extractionText();
    }

}
