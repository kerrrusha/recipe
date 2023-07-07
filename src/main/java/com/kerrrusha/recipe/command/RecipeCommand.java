package com.kerrrusha.recipe.command;

import com.kerrrusha.recipe.model.Difficulty;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecipeCommand {

    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servingsMin;
    private Integer servingsMax;
    private String url;
    private String directions;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Difficulty difficulty;
    private NotesCommand notes;
    private Set<CategoryCommand> categories = new HashSet<>();

    public String getServingsString() {
        return servingsMin.equals(servingsMax)
                ? "" + servingsMin
                : servingsMin + " - " + servingsMax;
    }

}
