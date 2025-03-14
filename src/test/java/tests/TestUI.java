package tests;

import org.junit.jupiter.api.*;
import pages.*;
import utils.ConfigReader;
import webHooks.WebHooks;

import java.util.List;


public class TestUI extends WebHooks {

    private String firstNumberTask, secondNumberTask;

    @Test
    @DisplayName("Проверка аутентификации")
    public void AuthTest() {
        new LoginPage()
                .authMethods(ConfigReader.getProperty("login"), ConfigReader.getProperty("password"));
        Assertions.assertTrue(MainPage.checkUserProfile());
    }

    @Test
    @DisplayName("Проверка перехода в проект TEST")
    public void moveProjectTest() {
        new LoginPage()
                .authMethods(ConfigReader.getProperty("login"), ConfigReader.getProperty("password"))
                .moveTasksPage();
        Assertions.assertTrue(TasksPage.checkMoveInProjectTest());
    }

    @Test
    @DisplayName("Проверка счетчика задач")
    public void CountTasksTest() {
        firstNumberTask = new LoginPage()
                .authMethods(ConfigReader.getProperty("login"), ConfigReader.getProperty("password"))
                .moveTasksPage()
                .countTasksMethod();
        secondNumberTask = new TasksPage()
                .initCreateTask()
                .createTask(ConfigReader.getProperty("themeText"))
                .loadNewTask()
                .countTasksMethod();
        Assertions.assertNotEquals(firstNumberTask, secondNumberTask);
    }

    @Test
    @DisplayName("Проверка статуса задачи")
    public void StatusTaskTest() {
        firstNumberTask = new LoginPage()
                .authMethods(ConfigReader.getProperty("login"), ConfigReader.getProperty("password"))
                .moveTasksPage()
                .countTasksMethod();
        secondNumberTask = new TasksPage()
                .initCreateTask()
                .createTask(ConfigReader.getProperty("themeText"))
                .loadNewTask()
                .countTasksMethod();
        new TasksPage()
                .searchTask(ConfigReader.getProperty("nameTask"))
                .checkStatusTask();
        List<String> statusTask = new OneTaskPage().checkStatusTask();
        Assertions.assertEquals(ConfigReader.getProperty("status"), statusTask.get(0));
        Assertions.assertEquals(ConfigReader.getProperty("readVersion"), statusTask.get(1));
    }

    @Test
    @DisplayName("Тест на создание нового бага с описанием")
    public void createBugTest() {
        firstNumberTask = new LoginPage()
                .authMethods(ConfigReader.getProperty("login"), ConfigReader.getProperty("password"))
                .moveTasksPage()
                .countTasksMethod();
        secondNumberTask = new TasksPage()
                .initCreateTask()
                .createTask(ConfigReader.getProperty("themeText"))
                .loadNewTask()
                .countTasksMethod();
        new TasksPage()
                .searchTask(ConfigReader.getProperty("nameTask"))
                .checkStatusTask();
        new TasksPage()
                .initCreateTask()
                .createNewBug(ConfigReader.getProperty("themeBugText"), ConfigReader.getProperty("descpiption"))
                .moveToBug()
                .inWorkTask()
                .doneBusinessTask();
        List<String> statBug = new OneTaskPage().checkStatusTask();
        Assertions.assertEquals(ConfigReader.getProperty("statusBug"), statBug.get(0));
    }
}

