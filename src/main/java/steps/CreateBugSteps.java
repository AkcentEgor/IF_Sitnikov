package steps;

import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import pages.CreateTaskPage;
import pages.OneTaskPage;
import pages.TasksPage;
import utils.ConfigReader;

import java.util.List;

public class CreateBugSteps {
    @И("Пользователь заполняет тему, описание и версии бага")
    public void createBug() {
        new CreateTaskPage()
                .createNewBug(ConfigReader.getProperty("themeBugText"), ConfigReader.getProperty("descpiption"));
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
        Assertions.assertEquals(ConfigReader.getProperty("statusBug"), statBug.get(0));
    }
}
