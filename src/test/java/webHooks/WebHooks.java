package webHooks;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import utils.ConfigReader;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class WebHooks {
    @Before
    public void initBrowser(){
        Configuration.timeout = 15000;
        Selenide.open(ConfigReader.getProperty("url"));
        getWebDriver().manage().window().maximize();
    }

    @After
    public void afterTest(){
        Selenide.closeWebDriver();
    }
}
