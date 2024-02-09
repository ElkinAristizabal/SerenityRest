package sqa.petstore.task.pet;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import sqa.petstore.models.pet.PostPetModel;
import sqa.petstore.questions.pet.BuildPet;

import static net.serenitybdd.screenplay.Tasks.instrumented;

public class PostPet implements Task {
    private final String path;
    private final String id;
    private final String nameCategory;
    private final String namePet;

    public PostPet(String path, String id, String nameCategory, String namePet) {
        this.path = path;
        this.id = id;
        this.nameCategory = nameCategory;
        this.namePet = namePet;
    }

    @Override
    public <T extends Actor> void performAs(T actor) {

        PostPetModel petInfo = actor.asksFor(BuildPet.was(id, nameCategory,namePet));

        actor.attemptsTo(
                Post.to(path).with(
                        requestSpecification -> requestSpecification
                                .contentType(ContentType.JSON)
                                .body(petInfo)
                )
        );
    }
    public static PostPet fromPet(String path, String id, String nameCategory, String namePet){
        return instrumented(PostPet.class, path, id, nameCategory, namePet);
    }
}
