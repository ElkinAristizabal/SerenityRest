package sqa.petstore.models.pet;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Category {

    @JsonProperty("id")
    public Integer id;

    @JsonProperty("name")
    public String name;
}
