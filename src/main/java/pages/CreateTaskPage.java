package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.*;

public class CreateTaskPage {

    private final SelenideElement themeArea = $x("//input[@id='summary']").as("Поле ввода заголовка 'Тема'");
    private final SelenideElement createButtonSubmit = $x("//input[@id='create-issue-submit']").as("Кнопка 'Создать'");
    private final SelenideElement visionButtonDescription = $x("//div[@id='description-wiki-edit']//li[@data-mode='wysiwyg']/button")
            .as("Кнопка 'Визуальный' в окне 'Описание'");
    private final SelenideElement visionButtonEnv = $x("//div[@id='environment-wiki-edit']//li[@data-mode='wysiwyg']/button")
            .as("Кнопка 'Визуальный' в окне 'Окружение'");
    private final SelenideElement versionsWin = $x("//*[@id='fixVersions']")
            .as("Окно выбора версии (Описание)");
    private final SelenideElement versionsDesc = $x("//*[@id='fixVersions']/optgroup/option[2]")
            .as("Выбор версии в окне 'Описание'");
    private final SelenideElement versionsEnvWin = $x("//*[@id='versions']")
            .as("Окно выбора версии (Окружение)");
    private final SelenideElement versionsEnv = $x("//*[@id='versions']/optgroup/option[1]")
            .as("Выбор версии в окне 'Окружение'");
    private final SelenideElement iframe = $x("//iframe[@id='mce_0_ifr']").as("Окно создания задачи");
    private final SelenideElement textArea = $x("//body[@id='tinymce']").as("Текстовое поле 'Окружение'");

    public TasksPage createTask(String themeText) {
        themeArea.shouldBe(Condition.visible)
                .click();
        themeArea.setValue(themeText);
        clickOnButtonCreate();
        return Selenide.page(TasksPage.class);
    }

    public void clickOnButtonCreate() {
            createButtonSubmit.click();
        }

    public TasksPage createNewBug(String themeBugText, String descpiption) {
        iframe.shouldBe(Condition.visible, Duration.ofSeconds(10));
        Selenide.switchTo().frame(iframe);
        textArea.shouldBe(Condition.visible, Duration.ofSeconds(10)).setValue(descpiption);
        Selenide.switchTo().defaultContent();
        visionButtonDescription.click();
        visionButtonEnv.click();
        themeArea.setValue(themeBugText);
        versionsWin.click();
        versionsDesc.click();
        versionsEnvWin.click();
        versionsEnv.click();
        clickOnButtonCreate();
        return Selenide.page(TasksPage.class);
    }
}
