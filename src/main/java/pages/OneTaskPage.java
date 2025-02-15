package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;
import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$x;

public class OneTaskPage {

    private final SelenideElement statusTask = $x("//span[@id='status-val']/child::span").as("Статус задачи");
    private final SelenideElement readVersionTask = $x("//span[@id='fixVersions-field']/child::a").as("Статус версии");
    private final SelenideElement workButton = $x("//a[@id='action_id_21']/child::span").as("Кнопка 'В работе'");
    private final SelenideElement businessButton = $x("//a[@id='opsbar-transitions_more']").as("Кнопка 'Бизнес-процесс'");
    private final SelenideElement doneButton =  $x("//aui-item-link[@id='action_id_31']").as("Кнопка 'Выполнено'");

    public List<String> checkStatusTask() {
        return List.of(statusTask.getText(), readVersionTask.getText());
    }

    public OneTaskPage inWorkTask() {
        workButton.shouldBe(Condition.visible, Duration.ofSeconds(10))
                .click();
        statusTask.shouldHave(text("В РАБОТЕ"));
        return this;
    }

    public OneTaskPage doneBusinessTask() {
        businessButton.click();
        doneButton.click();
        statusTask.shouldHave(text("ГОТОВО"));
        return this;
    }
}
