package sbpTests;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;

public class BaseSteps {
    private final SelenideElement logInButton = $x("//button[@id='login-button']");
    private final SelenideElement userNameInput = $x("//input[@name='username']");
    private final SelenideElement passwordInput = $x("//input[@name='password']");
    private final SelenideElement smsInput = $x("//input[@id='otp-code']");
    private final SelenideElement codeButton = $x("//button[@id='login-otp-button']");
    private final SelenideElement appName = $x("//div[@class='environment print-hidden']");
    private final static SelenideElement logoImg = $x("//*[@id='logo']");
    private final SelenideElement curAva = $x("//a[@id='user-avatar']");
    private final SelenideElement newAva = $x("//div[@id='avatars']//img[@data-avatar='24.png']");
    private final SelenideElement labelAva = $x("//div[@id='avatars-form']/label");
    private final SelenideElement saveButton = $x("//*[@id=\"submit-button\"]");
    private final SelenideElement messageError = $x("//*[@id=\"alerts-container\"]/div[2]");
    private final SelenideElement cardButton = $x("//*[@id=\"cards-overview-index\"]");
    private final SelenideElement block = $x("//*[@id=\"card-details-ownbank-10066\"]/div[2]/div[2]/div[2]/div/div[2]/a");
    private final SelenideElement timeBlock = $x("//*[@id=\"block-card-reasons\"]/label[1]/input");
    private final SelenideElement blockButton = $x("//*[@id=\"block-card\"]");
    private final SelenideElement codCard = $x("//input[@name='otpCode']");
    private final SelenideElement confirm = $x("//button[@id='confirm']");
    private final SelenideElement messageCard = $x("//*[@id=\"card-details-ownbank-10066\"]/div[2]/div[2]/div[1]");
    private final SelenideElement unlockButton = $x("//*[@id=\"card-details-ownbank-10066\"]/div[2]/div[2]/div[2]/div/div[1]/a");

    @Test
    void authorization(){
        userNameInput.shouldBe(visible).val("demo");
        passwordInput.shouldBe(visible).val("demo");
        logInButton.shouldBe(visible).click();
        smsInput.shouldBe(visible).val("0000");
        codeButton.shouldBe(visible).click();
        logoImg.shouldBe(visible);
        appName.shouldBe(visible, Duration.ofSeconds(10));
    }
    @Step("Проверка наименования приложения")
    void appCheckName(){
        assertThat("Не соответствует текст", appName.getText(), containsString("Интернет-банк"));
    }
    @Step ("Изменить аватар")
    void setNewAva(){
        curAva.click();
        switchTo().frame($x("(//div[@id='contentbar']/iframe)"));
        labelAva.shouldBe(visible);
        labelAva.shouldHave(text("Аватар"));
        newAva.click();
        saveButton.click();
    }
    @Step("Проверка сообщения")
    void checkMessage(){
        assertThat("Не соответствует текст", messageError.getText(), containsString("Демо-пользователь не может менять настройки."));
    }
    @Step("Выйти из фрейма")
    void frameOut(){
        switchTo().defaultContent();
        logoImg.shouldBe(visible).click();
    }
    @Step("Временная блокировка карты")
    void blockCard(){
        cardButton.shouldBe(visible).click();
        block.shouldBe(visible).click();
        timeBlock.click();
        blockButton.shouldBe(visible).click();
        switchTo().frame($x("//div[@id='card-block']//iframe"));
        codCard.shouldBe(visible).val("0000");
        confirm.shouldBe(visible).click();
    }
    @Step("Подтверждение блокировки карты")
    void checkCardB(){
        assertThat("Не соответствует текст", messageCard.getText(), containsString("Заблокирована"));
    }
    @Step("Разблокирование карты")
    void unlockCard(){
        cardButton.shouldBe(visible).click();
        unlockButton.shouldBe(visible).click();
        switchTo().frame($x("//div[@id='card-unblock']//iframe"));
        codCard.shouldBe(visible).val("0000");
        confirm.shouldBe(visible).click();
    }
    @Step("Подтверждение разблокировки карты")
    void checkCard(){
        assertThat("Не соответствует текст", messageCard.getText(), containsString("Действует"));
    }
}
