package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$x;

public class MainPage {

    private final SelenideElement projectButton = $x("//a[@id='browse_link']").as("Кнопка 'Проекты'");
    private final SelenideElement testButton = $x("//a[@id='admin_main_proj_link_lnk']").as("Кнопка 'Test (TEST)'");
    private static final SelenideElement userProfile = $x("//a[@id='header-details-user-fullname']").as("Иконка профиля");

    public TasksPage moveTasksPage() {
        projectButton.click();
        testButton.click();
        return Selenide.page(TasksPage.class);
    }

    public static Boolean checkUserProfile() {
        userProfile.shouldBe(Condition.visible);
        return userProfile.isDisplayed();
    }
}
