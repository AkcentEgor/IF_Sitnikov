package steps;

import api.requresApi.ReqresApi;
import io.cucumber.java.ru.И;
import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import io.qameta.allure.Allure;
import models.reqres.User;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import utils.ConfigReader;
import utils.MapperUtils;

public class ReqresSteps {

    private static final ReqresApi reqresApi = new ReqresApi();
    private User fromFileUser;
    private User newUser;

    @Когда("Создать пользователя c данными из json файла {string}")
    public User readFromFileUser(String filePath) {
        fromFileUser = MapperUtils.readFromFile(filePath, User.class);
        return fromFileUser;
    }

    @И("Сменить созданному пользователю имя на {string} и работу на {string}")
    public void changeUserNameAndJob(String name, String job) {
        fromFileUser.setName(name);
        fromFileUser.setJob(job);
        Allure.addAttachment("Измененный пользователь из файла", fromFileUser.toString());
    }

    @И("Отправить запрос на создание данного пользователя на сервисе reqres")
    public User createNewUser() {
        newUser = reqresApi.postUser(fromFileUser)
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .body()
                .as(User.class);
        return newUser;
    }

    @Тогда("Проверить, валидность данных пользователя")
    public void checkResponse() {
        Assertions.assertEquals(HttpStatus.SC_CREATED, Integer.parseInt(ConfigReader.getProperty("reqres.status")));
        Assertions.assertEquals(fromFileUser.getName(), newUser.getName());
        Assertions.assertEquals(fromFileUser.getJob(), newUser.getJob());
        Allure.addAttachment("Результаты сравнения данных",
                String.format(
                        "Данные пользователя из файла:%n" +
                                "- Имя: %s%n" +
                                "- Работа: %s%n%n" +
                                "Последний персонаж в эпизоде:%n" +
                                "- Имя: %s%n" +
                                "- Работа: %s",
                        fromFileUser.getName(), fromFileUser.getJob(),
                        newUser.getName(), newUser.getJob()
                )
        );
    }
}