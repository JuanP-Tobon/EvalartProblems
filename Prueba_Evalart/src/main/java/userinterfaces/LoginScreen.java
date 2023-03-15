package userinterfaces;

import net.serenitybdd.screenplay.targets.Target;
import org.openqa.selenium.By;

public class LoginScreen {
    public static final Target INICIO_SESION_LABEL =Target.the("Label to indicates the login page")
            .locatedBy("//h1[text()='Iniciar sesión']");
    public static final Target USER_INPUT =Target.the("field to enter the username")
            .located(By.name("username"));
    public static final Target PASSWORD_INPUT =Target.the("field to enter the password")
            .located(By.name("password"));
    public static final Target SEND_BUTTON = Target.the("Button to send the login information")
            .locatedBy("//button[@type='submit']");

    private LoginScreen(){}
}
