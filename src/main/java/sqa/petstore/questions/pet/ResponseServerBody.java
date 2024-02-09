package sqa.petstore.questions.pet;

import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class ResponseServerBody implements Question <String>{


    @Override
    public String answeredBy(Actor actor) {
        return SerenityRest.lastResponse().body().asString();
    }
    @Override
    public String toString() {
        return answeredBy(Actor.named("user"));
    }
    public static ResponseServerBody was(){
        return new ResponseServerBody();
    }

}

