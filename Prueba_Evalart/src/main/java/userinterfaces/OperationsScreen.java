package userinterfaces;

import net.serenitybdd.screenplay.targets.Target;

public class OperationsScreen {
    public static final Target OPERATION_VALUE_LABEL = Target.the("The value to realice the math operation")
            .locatedBy("((//p[contains(text(),'Complete la siguiente operaci')])[1]/parent::*//child::*)[2]");
    public static final Target OPERATION2_VALUE_LABEL = Target.the("The value to realice the math second operation")
            .locatedBy("((//p[contains(text(),'Complete la siguiente operaci')])[2]/parent::*//child::*)[2]");
    public static final Target MATH_OPERATION_RESPONSE = Target.the("general field,checkbox,input, to select response")
            .locatedBy("(//p[contains(text(),'Complete la siguiente operaci')])[1]/parent::*//{0}");
    public static final Target MATH_OPERATION2_RESPONSE = Target.the("general field,checkbox,input, to select response 2")
            .locatedBy("(//p[contains(text(),'Complete la siguiente operaci')])[2]/parent::*//{0}");
    public static final Target WRITE_THE_WORD_LABEL = Target.the("write the n times the letter ").
            locatedBy("//p[contains(text(),'Escriba')]");
    public static final Target WRITE_THE_WORD_TEXT_AREA = Target.the("write the n times the letter ").
            locatedBy("//p[contains(text(),'Escriba')]/parent::*//textarea");
    public static final Target MULTIPLES_PROBLEM_LABEL = Target.the("label to indicate which of the numbers is multiple of...")
            .locatedBy("//p[contains(text(),'meros es m')]");
    public static final Target CHECKBOX_SELECT_MULTIPLES = Target.the("checkbox general to selects multiples")
            .locatedBy("(//p[contains(text(),'meros es m')]/parent::*//label)[{0}]");
    public static final Target INDICATES_THE_DATE_LABEL = Target.the("indicates the date operation")
            .locatedBy("//p[contains(text(),'Indique la fecha que corresponde')]");
    public static final Target DATE_INPUT_RESULT = Target.the("field to enter the final date")
            .locatedBy("//p[contains(text(),'Indique la fecha que corresponde')]/parent::*//input");


    private OperationsScreen(){}
}
