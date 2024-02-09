package sqa.petstore.questions.pet;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import sqa.petstore.models.pet.Category;
import sqa.petstore.models.pet.PostPetModel;
import sqa.petstore.models.pet.Tag;

import java.util.ArrayList;
import java.util.List;

public class BuildPet implements Question<PostPetModel> {

    private final String id;

    private final String nameCategory;

    private final String namePet;

    public BuildPet(String id, String nameCategory, String namePet) {
        this.id = id;
        this.nameCategory = nameCategory;
        this.namePet = namePet;
    }

    @Override
    public PostPetModel answeredBy(Actor actor) {

        Category category = Category.builder()
                .id(0)
                .name(nameCategory)
                .build();

        List<Tag> tags = new ArrayList<>();
        Tag tag = Tag.builder()
                .id(1)
                .name("")
                .build();

        tags.add(tag);

        return PostPetModel.builder()
                .id(Long.parseLong(id))
                .category(category)
                .name(namePet)
                .tags(tags)
                .build();

    }
    public static BuildPet was(String id, String nameCategory,String namePet){
        return new BuildPet(id, nameCategory, namePet);
    }

}
