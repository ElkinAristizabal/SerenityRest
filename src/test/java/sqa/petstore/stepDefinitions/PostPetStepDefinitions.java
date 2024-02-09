package sqa.petstore.stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.screenplay.Actor;
import sqa.petstore.constants.Constants;
import sqa.petstore.questions.pet.ResponseServerBody;
import sqa.petstore.questions.pet.ResponseServerCode;
import sqa.petstore.task.pet.CallToAPI;
import sqa.petstore.task.pet.PostPet;

import java.util.Map;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;


public class PostPetStepDefinitions {
    private final Actor user = Actor.named("user");
    private Integer test;

    @Given("user consumes the APIs endpoint")
    public void userConsumesTheAPIsEndpoint() {

        user.wasAbleTo(CallToAPI.en());
    }

    @When("user use the path pet to send the pet information with each {int}")
    public void userUseThePathPetToSendThePetInformationWithEachCase(Integer test) {
        this.test = test;
        Map<String, String> excelData = Constants.constanstExcelPet(test, "Sheet1");
        user.attemptsTo(
                PostPet.fromPet(excelData.get("path"), excelData.get("id"), excelData.get("nameCategory"), excelData.get("namePet"))
        );
    }

    @Then("user can validate the response service {int}")
    public void userCanValidateTheResponseService(Integer code) {
        user.should(
                seeThat("The response code is:", ResponseServerCode.was(), equalTo(code))
        );
        user.should(
                seeThat("The response body is:", ResponseServerBody.was(), equalTo(Constants.infoResponse(user,test,"Sheet1")))
        );
    }
}


