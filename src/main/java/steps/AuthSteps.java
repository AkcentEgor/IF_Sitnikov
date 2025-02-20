package steps;

import io.cucumber.java.ru.Когда;
import io.cucumber.java.ru.Тогда;
import org.junit.jupiter.api.Assertions;
import pages.LoginPage;
import pages.MainPage;
import utils.ConfigReader;

public class AuthSteps {
    @Когда("Пользователь вводит логин и пароль")
    public void authTest() {
        new LoginPage()
                .authMethods(ConfigReader.getProperty("login"), ConfigReader.getProperty("password"));
    }

    @Тогда("Отображается профиль пользователя")
    public void userProfileIsDisplayed() {
        Assertions.assertTrue(MainPage.checkUserProfile(), "Профиль пользователя не отображается");
    }
}
