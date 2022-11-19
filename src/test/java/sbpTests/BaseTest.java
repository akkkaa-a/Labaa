package sbpTests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class BaseTest {
    public BaseSteps baseSteps;
    private final static SelenideElement logoImg = $x("//*[@id='logo']");
    private final static String baseUrl = "https://idemo.bspb.ru/";

    @BeforeAll
    static void beforeConfig(){
        Configuration.timeout = 3000; // Умное ожидание появление элемента на странице
        Configuration.browserSize = "1920x1080"; // Умно
    }
    @BeforeEach
    void before(){
        open(baseUrl);
        logoImg.shouldBe(visible);
        baseSteps = new BaseSteps();
        baseSteps.authorization();
    }
    @AfterEach
    void after(){
        closeWebDriver();
    }
}
