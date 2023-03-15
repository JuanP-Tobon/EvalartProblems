package stepdefinitions;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.serenitybdd.screenplay.actions.Open;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.actors.OnlineCast;
import tasks.Login;
import tasks.SolveProblems;

public class EvalartTestStepDefinitions {


    @Before
    public void prepareStage() {
        OnStage.setTheStage(new OnlineCast());
        OnStage.theActorCalled("Usuario QA");
        OnStage.theActorInTheSpotlight().attemptsTo(
                Open.url("https://tasks.evalartapp.com/automatization/"));

    }

    @Given("^the user logs into the page$")
    public void theUserLogsIntoThePage() {
        OnStage.theActorInTheSpotlight().wasAbleTo(Login.with());
    }

    @When("^He performs the solution of the form ten times$")
    public void hePerformsTheSolutionOfTheFormTenTimes() {
        OnStage.theActorInTheSpotlight().attemptsTo(SolveProblems.on());
    }

    @Then("^he should see the hash code$")
    public void heShouldSeeTheHashCode() {

    }
}
