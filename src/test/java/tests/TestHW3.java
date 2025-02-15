package tests;

import org.junit.jupiter.api.*;
import pages.*;
import utils.ConfigReader;
import webHooks.WebHooks;

import java.util.List;


public class TestHW3 extends WebHooks {

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

    @Test
    @DisplayName("Проверка аутентификации")
    public void AuthTest() {
        new LoginPage()
                .authMethods(login, password);
        Assertions.assertTrue(MainPage.checkUserProfile());
    }

    @Test
    @DisplayName("Проверка перехода в проект TEST")
    public void moveProjectTest() {
        new LoginPage()
                .authMethods(login, password)
                .moveTasksPage();
        Assertions.assertTrue(TasksPage.checkMoveInProjectTest());
    }

    @Test
    @DisplayName("Проверка счетчика задач")
    public void CountTasksTest() {
        firstNumberTask = new LoginPage()
                .authMethods(login, password)
                .moveTasksPage()
                .countTasksMethod();
        secondNumberTask = new TasksPage()
                .initCreateTask()
                .createTask(themeText)
                .loadNewTask()
                .countTasksMethod();
        Assertions.assertNotEquals(firstNumberTask, secondNumberTask);
    }

    @Test
    @DisplayName("Проверка статуса задачи")
    public void StatusTaskTest() {
        firstNumberTask = new LoginPage()
                .authMethods(login, password)
                .moveTasksPage()
                .countTasksMethod();
        secondNumberTask = new TasksPage()
                .initCreateTask()
                .createTask(themeText)
                .loadNewTask()
                .countTasksMethod();
        new TasksPage()
                .searchTask(nameTask)
                .checkStatusTask();
        List<String> statusTask = new OneTaskPage().checkStatusTask();
        Assertions.assertEquals(status, statusTask.get(0));
        Assertions.assertEquals(readVersion, statusTask.get(1));
    }

    @Test
    @DisplayName("Тест на создание нового бага с описанием")
    public void createBugTest() {
        firstNumberTask = new LoginPage()
                .authMethods(login, password)
                .moveTasksPage()
                .countTasksMethod();
        secondNumberTask = new TasksPage()
                .initCreateTask()
                .createTask(themeText)
                .loadNewTask()
                .countTasksMethod();
        new TasksPage()
                .searchTask(nameTask)
                .checkStatusTask();
        List<String> statusTask = new OneTaskPage().checkStatusTask();
        new TasksPage()
                .initCreateTask()
                .createNewBug(themeBugText, descpiption)
                .moveToBug()
                .inWorkTask()
                .doneBusinessTask();
        List<String> statBug = new OneTaskPage().checkStatusTask();
        Assertions.assertEquals(statusBug, statBug.get(0));
    }
}

