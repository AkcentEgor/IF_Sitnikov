package requresTests;

import models.requres.User;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import steps.RequresSteps;
import utils.ConfigReader;

public class RequresCreateUserTest {

    RequresSteps requresSteps = new RequresSteps();

    @Test
    @DisplayName("Проверка на создание пользователя и валидность данных")
    public void checkCreateNewUser() {
        User user = requresSteps.readFromFileUser(ConfigReader.getProperty("requres_path_json"));
        user.setName(ConfigReader.getProperty("requres_name_user"));
        user.setJob(ConfigReader.getProperty("requres_job_user"));
        User newUser = requresSteps.createNewUser(user);

        Assertions.assertEquals(HttpStatus.SC_CREATED, Integer.parseInt(ConfigReader.getProperty("requres_status")));
        Assertions.assertEquals(user.getName(), newUser.getName());
        Assertions.assertEquals(user.getJob(), newUser.getJob());

    }
}
