package sbpTests;

import io.qameta.allure.Description;
import org.junit.jupiter.api.*;

public class LoginTest extends BaseTest {
    @Test
    @Description("Изменение аватара")
    void avatar(){
        baseSteps.appCheckName();
        baseSteps.setNewAva();
        baseSteps.checkMessage();
        baseSteps.frameOut();
    }

    @Test
    @Description("Блокировка и разблокировка карты")
    void card(){
        baseSteps.blockCard();
        baseSteps.checkCardB();
        baseSteps.unlockCard();
        baseSteps.checkCard();
        baseSteps.frameOut();
    }
}
