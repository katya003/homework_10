import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Story;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

@Feature("Issue в репозитории")
@Story("Просмотр Issue")
@Owner("Ekaterina")

public class StepsTest {

    private static final String REPOSITORY = "katya003/homework_10";

    @Test
    @DisplayName("Проверка наличия Issue при помощи lambda")
    public void lambdaStepTest()  {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открыть главную страницу", () -> {
            open("https://github.com");
        });
        step("Искать репозиторий " + REPOSITORY, () -> {

            $(".header-search-button").click();
            $("#query-builder-test").setValue(REPOSITORY).submit();
        });
        step("Кликнуть по ссылке репозитория " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Проверить наличие Issues", () -> {
            $("#issues-tab").shouldHave(text("Issues"));
        });

    }

    @Test
    @DisplayName("Проверка наличия Issue при помощи Step")
    public void annotatedStepTest() {
        WebSteps steps = new WebSteps();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.seeIssues();
    }
}
