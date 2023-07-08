package com.kerrrusha.recipe.converter.ingredient;

import com.kerrrusha.recipe.command.IngredientCommand;
import com.kerrrusha.recipe.converter.uom.UnitOfMeasureToUnitOfMeasureCommandConverter;
import com.kerrrusha.recipe.model.Ingredient;
import lombok.RequiredArgsConstructor;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class IngredientToIngredientCommandConverter implements Converter<Ingredient, IngredientCommand> {

    private final UnitOfMeasureToUnitOfMeasureCommandConverter uomConverter;

    @Nullable
    @Override
    @Synchronized
    public IngredientCommand convert(@Nullable Ingredient ingredient) {
        if (isNull(ingredient)) {
            return null;
        }

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setId(ingredient.getId());
        if (nonNull(ingredient.getRecipe())) {
            ingredientCommand.setRecipeId(ingredient.getRecipe().getId());
        }
        ingredientCommand.setAmount(ingredient.getAmount());
        ingredientCommand.setDescription(ingredient.getDescription());
        ingredientCommand.setUnitOfMeasure(uomConverter.convert(ingredient.getUnitOfMeasure()));

        return ingredientCommand;
    }

}
