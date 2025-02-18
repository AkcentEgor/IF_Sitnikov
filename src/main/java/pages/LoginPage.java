package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$x;

public class LoginPage {

    private final SelenideElement loginInput = $x("//input[@name='os_username']").as("Ввод логина");
    private final SelenideElement passwordInput = $x("//input[@name='os_password']").as("Ввод пароля");
    private final SelenideElement loginButton = $x("//input[@name='login']").as("Кнопка 'Войти'");

    public MainPage authMethods(String login, String password) {
        setFieldCredential(login, password);
        pressLoginButton();
        return Selenide.page(MainPage.class);
    }

    public void setFieldCredential(String login, String password) {
        loginInput.shouldBe(Condition.visible, Duration.ofSeconds(10))
                .setValue(login);
        passwordInput.setValue(password);
    }

    public void pressLoginButton() {
        loginButton.click();
    }
}
