package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class TasksPage {

    private static final SelenideElement headTaskPage = $x("//span[@id='issues-subnavigation-title']").as("Текст 'Открытые задачи'");
    private final SelenideElement countTasks = $x("//div[@class='showing']/child::span").as("Счетчик задач: 1 из ...");
    private final SelenideElement createButton = $x("//a[@id='create_link']").as("Кнопка 'Создать'");
    private final SelenideElement newTask = $x("//ol[@class='issue-list']/li[1]/a").as("Новая задача в списке");
    private final SelenideElement searchInput = $x("//input[@id='quickSearchInput']").as("Поле для ввода 'Поиск'");
    private final SelenideElement newBug = $x("//*[@id='aui-flag-container']/div/div/a").as("Номер бага в всплывающем окне");
    private final SelenideElement loadPage = $x("//*[@id='jira']").as("Загрузка страницы");

    @Step("Нажать кнопку 'Создать'")
    public CreateTaskPage initCreateTask() {
        createButton.shouldBe(Condition.visible)
                .click();
        return Selenide.page(CreateTaskPage.class);
    }

    @Step("Проверить, что открылся проект 'ТЕСТ'")
    public static Boolean checkMoveInProjectTest() {
        headTaskPage.shouldBe(Condition.visible);
        return headTaskPage.isDisplayed();
    }

    @Step("Получить общее число задач")
    public String countTasksMethod() {
        return countTasks.getText();
    }

    @Step("Убедиться, что новая задача создана и счётчик обновился")
    public TasksPage loadNewTask() {
        Selenide.refresh();
        loadPage.shouldBe(Condition.visible, Duration.ofSeconds(10));
        newTask.shouldBe(Condition.visible, Duration.ofSeconds(10));
        Selenide.refresh();
        return this;
    }

    @Step("Найти задачу {nameTask}")
    public OneTaskPage searchTask(String nameTask) {
        searchInput.click();
        searchInput.setValue(nameTask).pressEnter();
        return Selenide.page(OneTaskPage.class);
    }

    @Step("Перейти в созданный баг")
    public OneTaskPage moveToBug() {
        newBug.click();
        return Selenide.page(OneTaskPage.class);
    }
}

