package com.kerrrusha.recipe.service.impl;

import com.kerrrusha.recipe.command.IngredientCommand;
import com.kerrrusha.recipe.converter.ingredient.IngredientCommandToIngredientConverter;
import com.kerrrusha.recipe.converter.ingredient.IngredientToIngredientCommandConverter;
import com.kerrrusha.recipe.model.Ingredient;
import com.kerrrusha.recipe.model.Recipe;
import com.kerrrusha.recipe.repository.IngredientRepository;
import com.kerrrusha.recipe.repository.RecipeRepository;
import com.kerrrusha.recipe.repository.UnitOfMeasureRepository;
import com.kerrrusha.recipe.service.IngredientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    private final IngredientCommandToIngredientConverter ingredientCommandToIngredientConverter;
    private final IngredientToIngredientCommandConverter ingredientToIngredientCommandConverter;

    @Override
    public Set<IngredientCommand> findAllCommands() {
        Set<IngredientCommand> result = new HashSet<>();
        ingredientRepository.findAll().iterator().forEachRemaining(e -> result.add(ingredientToIngredientCommandConverter.convert(e)));
        return result;
    }

    @Override
    public IngredientCommand findCommandById(Long id) {
        return ingredientToIngredientCommandConverter.convert(findById(id));
    }

    private Ingredient findById(Long id) {
        return ingredientRepository.findById(id).orElseThrow(() -> new RuntimeException("Ingredient was not found by given id: " + id));
    }

    @Override
    @Transactional
    public IngredientCommand saveIngredientCommand(IngredientCommand command) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if (recipeOptional.isEmpty()) {
            //todo handle error
            log.error("Recipe not found for id: " + command.getRecipeId());
            return new IngredientCommand();
        }
        Recipe recipe = recipeOptional.get();

        Optional<Ingredient> ingredientOptional = recipe
                .getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(command.getId()))
                .findFirst();

        if (ingredientOptional.isPresent()) {
            Ingredient ingredientFound = ingredientOptional.get();
            ingredientFound.setDescription(command.getDescription());
            ingredientFound.setAmount(command.getAmount());
            ingredientFound.setUnitOfMeasure(unitOfMeasureRepository
                    .findById(command.getUnitOfMeasure().getId())
                    .orElseThrow(() -> new RuntimeException("UnitOfMeasure NOT FOUND for ingredient.id = " + ingredientFound.getId())));
        } else {
            //add new Ingredient
            recipe.addIngredient(ingredientCommandToIngredientConverter.convert(command));
        }

        Recipe savedRecipe = recipeRepository.save(recipe);

        Ingredient savedIngredient = savedRecipe.getIngredients().stream()
                .filter(recipeIngredients -> recipeIngredients.getId().equals(command.getId()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Ingredient not found in recipe.id = " + savedRecipe.getId()));

        return ingredientToIngredientCommandConverter.convert(savedIngredient);
    }

    @Override
    public void deleteById(Long id) {
        ingredientRepository.deleteById(id);
    }

}
