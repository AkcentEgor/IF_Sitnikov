package steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import pages.CreateTaskPage;
import pages.TasksPage;
import utils.ConfigReader;

public class CreateTaskSteps {
    private String firstNumberTask, secondNumberTask;

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
                .createTask(ConfigReader.getProperty("themeText"));
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
}
