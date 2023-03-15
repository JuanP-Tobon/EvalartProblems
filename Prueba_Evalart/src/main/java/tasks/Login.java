package tasks;

import cucumber.api.DataTable;
import groovy.util.logging.Log;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import userinterfaces.LoginScreen;

import java.util.Map;

public class Login implements Task {

    @Override
    public <T extends Actor> void performAs(T actor) {
        actor.attemptsTo(
                Enter.theValue("565421").into(LoginScreen.USER_INPUT),
                Enter.theValue("10df2f32286b7120Mi00LTEyNDU2NQ==30e0c83e6c29f1c3").into(LoginScreen.PASSWORD_INPUT),
                Click.on(LoginScreen.SEND_BUTTON)
        );

    }

    public static Login with() {
        return Tasks.instrumented(Login.class);
    }

}
