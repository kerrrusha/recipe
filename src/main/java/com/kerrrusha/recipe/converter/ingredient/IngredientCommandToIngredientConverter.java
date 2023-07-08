package com.kerrrusha.recipe.converter.ingredient;

import com.kerrrusha.recipe.command.IngredientCommand;
import com.kerrrusha.recipe.converter.uom.UnitOfMeasureCommandToUnitOfMeasureConverter;
import com.kerrrusha.recipe.model.Ingredient;
import com.kerrrusha.recipe.model.Recipe;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
@RequiredArgsConstructor
public class IngredientCommandToIngredientConverter implements Converter<IngredientCommand, Ingredient> {

    private final UnitOfMeasureCommandToUnitOfMeasureConverter uomConverter;

    @Nullable
    @Override
    @Synchronized
    public Ingredient convert(@Nullable IngredientCommand source) {
        if (isNull(source)) {
            return null;
        }

        final Ingredient ingredient = new Ingredient();
        ingredient.setId(source.getId());
        if (source.getRecipeId() != null) {
            Recipe recipe = Recipe.builder().id(source.getRecipeId()).build();
            recipe.addIngredient(ingredient);

            ingredient.setRecipe(recipe);
        }
        ingredient.setAmount(source.getAmount());
        ingredient.setDescription(source.getDescription());
        ingredient.setUnitOfMeasure(uomConverter.convert(source.getUnitOfMeasure()));
        return ingredient;
    }

}
