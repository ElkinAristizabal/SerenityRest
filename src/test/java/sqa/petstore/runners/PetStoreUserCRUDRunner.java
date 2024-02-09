package sqa.petstore.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
        features = "src/test/resources/features/PetStoreUserCRUD.feature",
        glue = "sqa.petstore.stepDefinitions",
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class PetStoreUserCRUDRunner {
}
