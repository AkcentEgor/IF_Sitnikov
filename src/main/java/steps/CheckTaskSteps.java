package steps;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import pages.OneTaskPage;
import pages.TasksPage;
import utils.ConfigReader;

import java.util.List;

public class CheckTaskSteps {
    @Когда("Пользователь в окне поиска вводит название задачи и нажимает Enter")
    public void searchTask() {
        new TasksPage()
                .searchTask(ConfigReader.getProperty("nameTask"));
    }

    @Тогда("Происходит проверка статуса задачи")
    public void checkStatus() {
        new OneTaskPage()
                .checkStatusTask();
        List<String> statusTask = new OneTaskPage().checkStatusTask();
        Assertions.assertEquals(ConfigReader.getProperty("status"), statusTask.get(0));
        Assertions.assertEquals(ConfigReader.getProperty("readVersion"), statusTask.get(1));
    }
}
