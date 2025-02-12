package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Selenide.*;

public class CreateTaskPage {

    private final SelenideElement themeArea = $x("//input[@id='summary']").as("Поле ввода заголовка 'Тема'");
    private final SelenideElement createButtonSubmit = $x("//input[@id='create-issue-submit']").as("Кнопка 'Создать'");
    private final SelenideElement visionButtonDescription = $x("//*[@id='description-wiki-edit']/nav/div/div/ul/li[1]/button")
            .as("Кнопка 'Визуальный' в окне 'Описание'");
    private final SelenideElement visionButtonEnv = $x("//*[@id='environment-wiki-edit']/nav/div/div/ul/li[1]/button")
            .as("Кнопка 'Визуальный' в окне 'Окружение'");
    private final SelenideElement versionsWin = $x("//*[@id='fixVersions']")
            .as("Окно выбора версии (Описание)");
    private final SelenideElement versionsDesc = $x("//*[@id='fixVersions']/optgroup/option[2]")
            .as("Выбор версии в окне 'Описание'");
    private final SelenideElement versionsEnvWin = $x("//*[@id='versions']")
            .as("Окно выбора версии (Окружение)");
    private final SelenideElement versionsEnv = $x("//*[@id='versions']/optgroup/option[1]")
            .as("Выбор версии в окне 'Окружение'");

    public TasksPage createTask(String themeText) {
        themeArea.shouldBe(Condition.visible)
                .click();
        themeArea.setValue(themeText);
        createButtonSubmit.click();
        return Selenide.page(TasksPage.class);
    }

    public TasksPage createNewBug(String themeBugText) {
        visionButtonDescription.click();
        visionButtonEnv.click();
        themeArea.setValue(themeBugText);
        versionsWin.click();
        versionsDesc.click();
        versionsEnvWin.click();
        versionsEnv.click();
        createButtonSubmit.click();
        return Selenide.page(TasksPage.class);
    }
}
