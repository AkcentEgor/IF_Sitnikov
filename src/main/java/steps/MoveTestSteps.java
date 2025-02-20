package steps;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import pages.MainPage;
import pages.TasksPage;

public class MoveTestSteps {
    @Когда("Пользователь переходит на страницу проекта TEST")
    public void moveProjectTest() {
        new MainPage()
                .moveTasksPage();
    }

    @Тогда("Открыта cтраница проекта TEST")
    public void taskPageIsDisplayed() {
        Assertions.assertTrue(TasksPage.checkMoveInProjectTest(), "Страница проекта Test (TEST) не отображается");
    }
}
