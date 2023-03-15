package tasks;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.Tasks;
import net.serenitybdd.screenplay.actions.Click;
import net.serenitybdd.screenplay.actions.Enter;
import net.serenitybdd.screenplay.actions.SelectFromOptions;
import net.serenitybdd.screenplay.actions.SendKeys;
import net.serenitybdd.screenplay.conditions.Check;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.targets.Target;
import org.apache.commons.lang3.StringUtils;
import userinterfaces.LoginScreen;
import userinterfaces.OperationsScreen;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

public class SolveProblems implements Task {
    @Override
    public <T extends Actor> void performAs(T actor) {
        for (int i = 0; i < 10; i++) {
            if (Target.the("Animals problem").locatedBy("//p[contains(text(),'Indique cuantos')]").resolveFor(actor).isVisible()) {
                String[] animalVec = Text.of(Target.the("Animals problem").locatedBy("//p[contains(text(),'Indique cuantos')]")).viewedBy(actor).asString().split(" ");
                String animalSearch = animalVec[2];
                String animals = Text.of(Target.the("animals String").locatedBy("(//p[contains(text(),'Indique cuantos')]/parent::*/child::*)[2]")).viewedBy(actor).asString();
                String[] vect = animals.split(animalSearch);

                actor.attemptsTo(Enter.theValue(String.valueOf(vect.length - 1)).into(Target.the("quantityAnimals").locatedBy("//p[contains(text(),'Indique cuantos')]/parent::*/parent::*//input")));
            }

            if (Target.the("Operations problem").locatedBy("(//p[contains(text(),'Complete la siguiente operaci')])[1]").resolveFor(actor).isVisible()) {
                String operation = Text.of(OperationsScreen.OPERATION_VALUE_LABEL).viewedBy(actor).asString().replace("=", "").replace("?", "");
                ScriptEngineManager manager = new ScriptEngineManager();
                ScriptEngine engine = manager.getEngineByName("js");
                try {
                    Object operationObject = engine.eval(operation);

                    actor.attemptsTo(
                            Check.whether(OperationsScreen.MATH_OPERATION_RESPONSE.of("select").resolveFor(actor).isVisible()).andIfSo(
                                    SelectFromOptions.byValue(operationObject.toString()).from(OperationsScreen.MATH_OPERATION_RESPONSE.of("select"))
                            ),

                            Check.whether(OperationsScreen.MATH_OPERATION_RESPONSE.of("input[@value='" + operationObject.toString() + "']").resolveFor(actor).isVisible()).andIfSo(
                                    Click.on(OperationsScreen.MATH_OPERATION_RESPONSE.of("input[@value='" + operationObject.toString() + "']"))
                            )
                    );
                } catch (ScriptException e) {
                    throw new RuntimeException(e);
                }
            }

            if (Target.the("Operations problem").locatedBy("(//p[contains(text(),'Complete la siguiente operaci')])[2]").resolveFor(actor).isVisible()) {
                String operation = Text.of(OperationsScreen.OPERATION2_VALUE_LABEL).viewedBy(actor).asString().replace("=", "").replace("?", "");
                ScriptEngineManager manager = new ScriptEngineManager();
                ScriptEngine engine = manager.getEngineByName("js");
                try {
                    Object operationObject = engine.eval(operation);

                    actor.attemptsTo(
                            Check.whether(OperationsScreen.MATH_OPERATION2_RESPONSE.of("select").resolveFor(actor).isVisible()).andIfSo(
                                    SelectFromOptions.byValue(operationObject.toString()).from(OperationsScreen.MATH_OPERATION2_RESPONSE.of("select"))
                            ),

                            Check.whether(OperationsScreen.MATH_OPERATION2_RESPONSE.of("input[@value='" + operationObject.toString() + "']").resolveFor(actor).isVisible()).andIfSo(
                                    Click.on(OperationsScreen.MATH_OPERATION2_RESPONSE.of("input[@value='" + operationObject.toString() + "']"))
                            )
                    );
                } catch (ScriptException e) {
                    throw new RuntimeException(e);
                }
            }

            if (OperationsScreen.WRITE_THE_WORD_LABEL.resolveFor(actor).isVisible()) {
                String[] words = Text.of(OperationsScreen.WRITE_THE_WORD_LABEL).viewedBy(actor).asString().replace("'", "").split(" ");
                int quantity = Integer.parseInt(words[1]);
                String letter = words[5];
                actor.attemptsTo(Enter.theValue(StringUtils.repeat(letter, quantity)).into(OperationsScreen.WRITE_THE_WORD_TEXT_AREA));
            }

            if (OperationsScreen.MULTIPLES_PROBLEM_LABEL.resolveFor(actor).isVisible()) {
                String[] words = Text.of(OperationsScreen.MULTIPLES_PROBLEM_LABEL).viewedBy(actor).asString().replace("?", "").replace("¿", "").split(" ");
                int number = Integer.parseInt(words[7]);
                for (int j = 1; j <= 25; j++) {
                    if (OperationsScreen.CHECKBOX_SELECT_MULTIPLES.of(String.valueOf(j)).resolveFor(actor).isVisible()) {
                        int value = Integer.parseInt(Text.of(OperationsScreen.CHECKBOX_SELECT_MULTIPLES.of(String.valueOf(j))).viewedBy(actor).asString());
                        if (value % number == 0)
                            actor.attemptsTo(Click.on(OperationsScreen.CHECKBOX_SELECT_MULTIPLES.of(String.valueOf(j))));
                    }
                }
            }

            if (OperationsScreen.INDICATES_THE_DATE_LABEL.resolveFor(actor).isVisible()) {
                String[] words = Text.of(OperationsScreen.INDICATES_THE_DATE_LABEL).viewedBy(actor).asString().split(" ");
                if (!Arrays.toString(words).contains("antes")) {
                    int daysQuantity = Integer.parseInt(words[6]);
                    String[] dateVec = words[12].split("/");

                    LocalDate date = LocalDate.of(Integer.parseInt(dateVec[2]), Integer.parseInt(dateVec[1]), Integer.parseInt(dateVec[0]));
                    date = date.plusDays(daysQuantity);
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

                    actor.attemptsTo(SendKeys.of(date.format(dateTimeFormatter)).into(OperationsScreen.DATE_INPUT_RESULT));
                } else {
                    int daysQuantity = Integer.parseInt(words[6]);
                    String[] dateVec = words[13].split("/");

                    LocalDate date = LocalDate.of(Integer.parseInt(dateVec[2]), Integer.parseInt(dateVec[1]), Integer.parseInt(dateVec[0]));
                    date = date.minusDays(daysQuantity);
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

                    actor.attemptsTo(SendKeys.of(date.format(dateTimeFormatter)).into(OperationsScreen.DATE_INPUT_RESULT));
                }

            }

            actor.attemptsTo(Click.on(LoginScreen.SEND_BUTTON));
        }
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static SolveProblems on() {
        return Tasks.instrumented(SolveProblems.class);
    }
}
