package tests;

import org.junit.jupiter.api.*;
import pages.*;
import webHooks.WebHooks;

import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestHW3 extends WebHooks {

    private final LoginPage loginPage = new LoginPage();
    private final MainPage mainPage = new MainPage();
    private final TasksPage tasksPage = new TasksPage();
    private final CreateTaskPage createTaskPage = new CreateTaskPage();
    private final OneTaskPage oneTaskPage = new OneTaskPage();

    private final String login = "AT6";
    private final String password = "Qwerty123";
    private final String themeText = "Тест счетчика";
//    private int firstNumberTask, secondNumberTask;
    private String firstNumberTask, secondNumberTask;
    private final String nameTask = "TestSeleniumATHomework";
    private final String themeBugText = "Новый баг";
    private final String status = "СДЕЛАТЬ";
    private final String statusBug = "ГОТОВО";
    private final String readVersion = "Version 2.0";

    @Order(1)
    @Test
    @DisplayName("Проверка аунтификации")
    public void AuthTest() {
        loginPage.authMethods(login, password);
        Assertions.assertTrue(MainPage.checkUserProfile());
    }

    @Order(2)
    @Test
    @DisplayName("Проверка перехода в проект TEST")
    public void moveProjectTest() {
        loginPage.authMethods(login, password);

        mainPage.moveTasksPage();
        Assertions.assertTrue(TasksPage.checkMoveInProjectTest());
    }

    @Order(3)
    @Test
    @DisplayName("Проверка счетчика задач")
    public void CountTasksTest() {
        loginPage.authMethods(login, password);
        mainPage.moveTasksPage();

        firstNumberTask = tasksPage.countTasksMethod();
        tasksPage.initCreateTask();
        createTaskPage.createTask(themeText);
        tasksPage.loadNewTask();
        secondNumberTask = tasksPage.countTasksMethod();
        Assertions.assertNotEquals(firstNumberTask, secondNumberTask);
    }

    @Order(4)
    @Test
    @DisplayName("Проверка статуса задачи")
    public void StatusTaskTest() {
        loginPage.authMethods(login, password);
        mainPage.moveTasksPage();
        firstNumberTask = tasksPage.countTasksMethod();
        tasksPage.initCreateTask();
        createTaskPage.createTask(themeText);
        tasksPage.loadNewTask();
        secondNumberTask = tasksPage.countTasksMethod();

        tasksPage.searchTask(nameTask);
        oneTaskPage.checkStatusTask();
        List<String> statusTask = oneTaskPage.checkStatusTask();
        Assertions.assertEquals(status, statusTask.get(0));
        Assertions.assertEquals(readVersion, statusTask.get(1));
    }

    @Order(5)
    @Test
    @DisplayName("Тест на создание нового бага с описанием")
    public void createBugTest() {
        loginPage.authMethods(login, password);
        mainPage.moveTasksPage();
        firstNumberTask = tasksPage.countTasksMethod();
        tasksPage.initCreateTask();
        createTaskPage.createTask(themeText);
        tasksPage.loadNewTask();
        secondNumberTask = tasksPage.countTasksMethod();
        tasksPage.searchTask(nameTask);
        oneTaskPage.checkStatusTask();
        List<String> statusTask = oneTaskPage.checkStatusTask();

        tasksPage.initCreateTask();
        createTaskPage.createNewBug(themeBugText);
        tasksPage.moveToBug();
        oneTaskPage.inWorkTask();
        oneTaskPage.doneBusinessTask();
        List<String> statBug = oneTaskPage.checkStatusTask();
        Assertions.assertEquals(statusBug, statBug.get(0));
    }
}

