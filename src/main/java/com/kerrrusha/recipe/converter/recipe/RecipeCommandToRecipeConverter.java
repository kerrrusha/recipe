package com.kerrrusha.recipe.converter.recipe;

import com.kerrrusha.recipe.command.RecipeCommand;
import com.kerrrusha.recipe.converter.category.CategoryCommandToCategoryConverter;
import com.kerrrusha.recipe.converter.ingredient.IngredientCommandToIngredientConverter;
import com.kerrrusha.recipe.converter.notes.NotesCommandToNotesConverter;
import com.kerrrusha.recipe.model.Recipe;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RecipeCommandToRecipeConverter implements Converter<RecipeCommand, Recipe> {

    private final CategoryCommandToCategoryConverter categoryConveter;
    private final IngredientCommandToIngredientConverter ingredientConverter;
    private final NotesCommandToNotesConverter notesConverter;

    @Nullable
    @Override
    @Synchronized
    public Recipe convert(@Nullable RecipeCommand source) {
        if (source == null) {
            return null;
        }

        final Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setCookTime(source.getCookTime());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setDescription(source.getDescription());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setDirections(source.getDirections());
        recipe.setServingsMin(source.getServingsMin());
        recipe.setServingsMax(source.getServingsMax());
        recipe.setUrl(source.getUrl());
        recipe.setImage(source.getImage());
        recipe.setNotes(notesConverter.convert(source.getNotes()));

        if (source.getCategories() != null && source.getCategories().size() > 0) {
            source.getCategories()
                    .forEach(category -> recipe.getCategories().add(categoryConveter.convert(category)));
        }

        if (source.getIngredients() != null && source.getIngredients().size() > 0) {
            source.getIngredients()
                    .forEach(ingredient -> recipe.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        return recipe;
    }
}
