package steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import pages.*;
import utils.ConfigReader;

import java.util.List;

public class TestsSteps {

    private final String login = ConfigReader.getProperty("login");
    private final String password = ConfigReader.getProperty("password");
    private final String themeText = ConfigReader.getProperty("themeText");
    private final String nameTask = ConfigReader.getProperty("nameTask");
    private final String themeBugText = ConfigReader.getProperty("themeBugText");
    private final String status = ConfigReader.getProperty("status");
    private final String statusBug = ConfigReader.getProperty("statusBug");
    private final String readVersion = ConfigReader.getProperty("readVersion");
    private final String descpiption = ConfigReader.getProperty("descpiption");
    private String firstNumberTask, secondNumberTask;

    @Когда("Пользователь вводит логин и пароль")
    public void authTest() {
        new LoginPage()
                .authMethods(login, password);
    }

    @Тогда("Отображается профиль пользователя")
    public void userProfileIsDisplayed() {
        Assertions.assertTrue(MainPage.checkUserProfile(), "Профиль пользователя не отображается");
    }

    @Когда("Пользователь переходит на страницу проекта TEST")
    public void moveProjectTest() {
        new MainPage()
                .moveTasksPage();
    }

    @Тогда("Открыта cтраница проекта TEST")
    public void taskPageIsDisplayed() {
        Assertions.assertTrue(TasksPage.checkMoveInProjectTest(), "Страница проекта Test (TEST) не отображается");
    }

    @Дано("Подсчитано количество задач 1")
    public void countTasksFirst() {
        firstNumberTask = new TasksPage()
                .countTasksMethod();
    }

    @Когда("Пользователь создает новую задачу")
    public void createTask() {
        new TasksPage()
                .initCreateTask();
    }

    @И("Пользователь заполняет тему и нажимает кнопку Создать")
    public void inputTheme() {
        new CreateTaskPage()
                .createTask(themeText);
    }

    @И("Система переходит в открытые задачи и обновляет страницу")
    public void loadTask() {
        new TasksPage()
                .loadNewTask();
    }

    @Дано("Подсчитано количество задач 2")
    public void countTasksSecond() {
        secondNumberTask = new TasksPage()
                .countTasksMethod();
    }

    @Тогда("Система проверяет изменилось ли количество задач")
    public void checkUpdateCountTask() {
        Assertions.assertNotEquals(firstNumberTask, secondNumberTask);
    }

    @Когда("Пользователь в окне поиска вводит название задачи и нажимает Enter")
    public void searchTask() {
        new TasksPage()
                .searchTask(nameTask);
    }

    @Тогда("Происходит проверка статуса задачи")
    public void checkStatus() {
        new OneTaskPage()
                .checkStatusTask();
        List<String> statusTask = new OneTaskPage().checkStatusTask();
        Assertions.assertEquals(status, statusTask.get(0));
        Assertions.assertEquals(readVersion, statusTask.get(1));
    }

    @И("Пользователь заполняет тему, описание и версии бага")
    public void createBug() {
        new CreateTaskPage()
                .createNewBug(themeBugText, descpiption);
        }

    @И("Пользователь переходит в созданный баг")
    public void moveBug() {
        new TasksPage()
                .moveToBug();
    }

    @И("Пользователь берет задачу в работу и доводит до состояния ГОТОВО")
    public void doneBug() {
        new OneTaskPage()
                .inWorkTask()
                .doneBusinessTask();
    }

    @Тогда("Состояние задачи соответствует: ГОТОВО")
    public void checkStatusBug() {
        List<String> statBug = new OneTaskPage().checkStatusTask();
        Assertions.assertEquals(statusBug, statBug.get(0));
    }
}
