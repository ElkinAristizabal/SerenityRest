package sqa.petstore.stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.core.annotations.events.BeforeScenario;
import net.serenitybdd.screenplay.Actor;
import sqa.petstore.constants.Constants;
import sqa.petstore.questions.pet.ResponseServerBody;
import sqa.petstore.questions.pet.ResponseServerCode;
import sqa.petstore.questions.user.ResponseBodyUser;
import sqa.petstore.task.pet.CallToAPI;
import sqa.petstore.task.user.DeleteUser;
import sqa.petstore.task.user.GetUser;

import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class PetStoreUserDeleteStepDefinitions {
    private static final Actor user = Actor.named("user");
    static Map<String, String> path;

    @BeforeScenario
    public static void setUp () {
        user.wasAbleTo(CallToAPI.en());
        path = Constants.constanstExcelUser(1,"Path");
        user.attemptsTo(
                GetUser.fromUser(path.get("path") + path.get("pathget"))
        );
        user.should(
                seeThat("The response code is:", ResponseServerCode.was(), equalTo(200))
        );
        user.should(
                seeThat("The response body is:", ResponseServerBody.was(), equalTo(Constants.infoResponse(user,1,"UsersPut")))
        );
    }

    @When("user uses the path user to delete a user information")
    public void userUsesThePathUserToDeleteAUserInformation() {
        user.wasAbleTo(CallToAPI.en());
        user.attemptsTo(
                DeleteUser.fromUser(path.get("path") + path.get("pathget"))
        );
    }

    @Then("user can validate the response service delete code is {int}")
    public void userCanValidateTheResponseServiceCodeIs(Integer code) {
        user.should(
                seeThat("The response code is:", ResponseServerCode.was(), equalTo(code))
        );
    }

    @Then("the body response should has a message whit the users name")
    public void theBodyResponseShouldHasAMessageWhitTheUsersName() {
        Map<String, String> usersExcel = Constants.constanstExcelUser(1,"Users");
        user.should(
                seeThat("The message in the body is:", ResponseBodyUser.was(), equalTo(usersExcel.get("username")))
        );
    }

}
