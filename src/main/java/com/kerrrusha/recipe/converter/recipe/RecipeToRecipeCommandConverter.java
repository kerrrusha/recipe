package com.kerrrusha.recipe.converter.recipe;

import com.kerrrusha.recipe.command.RecipeCommand;
import com.kerrrusha.recipe.converter.category.CategoryToCategoryCommandConverter;
import com.kerrrusha.recipe.converter.ingredient.IngredientToIngredientCommandConverter;
import com.kerrrusha.recipe.converter.notes.NotesToNotesCommandConverter;
import com.kerrrusha.recipe.model.Category;
import com.kerrrusha.recipe.model.Recipe;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecipeToRecipeCommandConverter implements Converter<Recipe, RecipeCommand>{

    private final CategoryToCategoryCommandConverter categoryConveter;
    private final IngredientToIngredientCommandConverter ingredientConverter;
    private final NotesToNotesCommandConverter notesConverter;

    @Nullable
    @Override
    @Synchronized
    public RecipeCommand convert(@Nullable Recipe source) {
        if (source == null) {
            return null;
        }

        final RecipeCommand command = new RecipeCommand();
        command.setId(source.getId());
        command.setCookTime(source.getCookTime());
        command.setPrepTime(source.getPrepTime());
        command.setDescription(source.getDescription());
        command.setDifficulty(source.getDifficulty());
        command.setDirections(source.getDirections());
        command.setServingsMin(source.getServingsMin());
        command.setServingsMax(source.getServingsMax());
        command.setUrl(source.getUrl());
        command.setImage(source.getImage());
        command.setNotes(notesConverter.convert(source.getNotes()));

        if (source.getCategories() != null && source.getCategories().size() > 0) {
            source.getCategories()
                    .forEach((Category category) -> command.getCategories().add(categoryConveter.convert(category)));
        }

        if (source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients()
                    .forEach(ingredient -> command.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        return command;
    }
}
