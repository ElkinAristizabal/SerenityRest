package sqa.petstore.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import sqa.petstore.constants.Constants;
import sqa.petstore.questions.pet.ResponseServerBody;
import sqa.petstore.questions.pet.ResponseServerCode;
import sqa.petstore.task.pet.CallToAPI;
import sqa.petstore.task.user.GetUser;

import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class PetStoreUserReadStepDefinitions {

    private static final Actor user = Actor.named("user");
    private static Map<String, String> path;


    @When("user uses the path user to get a user information")
    public void userUsesThePathUserToGetAUserInformation() {
        user.wasAbleTo(CallToAPI.en());
        path = Constants.constanstExcelUser(1,"Path");
        user.attemptsTo(
                GetUser.fromUser(path.get("path") + path.get("pathget"))
        );
    }

    @Then("user can validate the response service get code is {int}")
    public void userCanValidateTheResponseServiceCodeIs(Integer code) {
        user.should(
                seeThat("The response code is:", ResponseServerCode.was(), equalTo(code))
        );
    }

    @And("the body response should has the user information")
    public void theBodyResponseShouldHasTheUserInformation() {

        user.should(
                seeThat("The response body is:", ResponseServerBody.was(), equalTo(Constants.infoResponse(user,1,"Users")))
        );
    }

}
