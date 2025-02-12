package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class TasksPage {

    private static final SelenideElement headTaskPage = $x("//span[@id='issues-subnavigation-title']").as("Текст 'Открытые задачи'");
    private final SelenideElement countTasks = $x("//div[@class='showing']/child::span").as("Счетчик задач: 1 из ...");
    private final SelenideElement createButton = $x("//a[@id='create_link']").as("Кнопка 'Создать'");
    private final SelenideElement newTask = $x("//ol[@class='issue-list']/li[1]/a").as("Новая задача в списке");
    private final SelenideElement searchInput = $x("//input[@id='quickSearchInput']").as("Поле для ввода 'Поиск'");
    private final SelenideElement newBug = $x("//*[@id='aui-flag-container']/div/div/a").as("Поле для ввода 'Поиск'");
    private final SelenideElement loadPage = $x("//*[@id='jira']").as("Загрузка страницы");

    public CreateTaskPage initCreateTask() {
        createButton.shouldBe(Condition.visible)
                .click();
        return Selenide.page(CreateTaskPage.class);
    }

    public static Boolean checkMoveInProjectTest() {
        headTaskPage.shouldBe(Condition.visible);
        return headTaskPage.isDisplayed();
    }

//        public int countTasksMethod() {
//        String countTasksText = countTasks.getText();
//        return Integer.parseInt(countTasksText.split(" ")[2]);
//    }

    public String countTasksMethod() {
        return countTasks.getText();
    }

    public TasksPage loadNewTask() {
        Selenide.refresh();
        loadPage.shouldBe(Condition.visible, Duration.ofSeconds(10));
        newTask.shouldBe(Condition.visible, Duration.ofSeconds(10));
        Selenide.refresh();
        return Selenide.page(TasksPage.class);
    }

    public OneTaskPage searchTask(String nameTask) {
        searchInput.click();
        searchInput.setValue(nameTask).pressEnter();
        return Selenide.page(OneTaskPage.class);
    }

    public OneTaskPage moveToBug() {
        newBug.click();
        return Selenide.page(OneTaskPage.class);
    }
}

