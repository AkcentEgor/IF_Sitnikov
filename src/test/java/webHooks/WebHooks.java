package webHooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import utils.ConfigReader;


import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class WebHooks {
    @BeforeEach
    public void initBrowser(){
        Configuration.timeout = 15000;
        Selenide.open(ConfigReader.getProperty("url"));
        getWebDriver().manage().window().maximize();
        SelenideLogger.addListener("AllureListener", new AllureSelenide().screenshots(Boolean.parseBoolean(ConfigReader.getProperty("save.screen"))));
    }

    @AfterEach
    public void afterTest(){
        Selenide.closeWebDriver();
    }
}
