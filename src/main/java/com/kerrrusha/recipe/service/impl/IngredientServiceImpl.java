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
        Recipe recipe = checkIfRecipeExists(command);

        Optional<Ingredient> ingredientOptional = recipe
                .getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(command.getId()))
                .findFirst();

        if (ingredientOptional.isPresent()) {
            updateIngredient(ingredientOptional.get(), command);
        } else {
            recipe.addIngredient(ingredientCommandToIngredientConverter.convert(command));
        }

        Recipe savedRecipe = recipeRepository.save(recipe);

//        Ingredient savedIngredient = tryGetSavedIngredientById(savedRecipe, command)
//                .orElse(tryGetSavedIngredientByOtherFields(savedRecipe, command));

        Optional<Ingredient> savedIngredientOptional = tryGetSavedIngredientById(savedRecipe, command);
        if (savedIngredientOptional.isEmpty()) {
            savedIngredientOptional = tryGetSavedIngredientByOtherFields(savedRecipe, command);
        }
        Ingredient savedIngredient = savedIngredientOptional
                .orElseThrow(() -> new RuntimeException("Ingredient not found in recipe.id = " + savedRecipe.getId()));

        return ingredientToIngredientCommandConverter.convert(savedIngredient);
    }

    private Optional<Ingredient> tryGetSavedIngredientById(Recipe savedRecipe, IngredientCommand command) {
        return savedRecipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(command.getId()))
                .findFirst();
    }

    private Optional<Ingredient> tryGetSavedIngredientByOtherFields(Recipe savedRecipe, IngredientCommand command) {
        return savedRecipe.getIngredients().stream()
                .filter(recipeIngredients -> recipeIngredients.getDescription().equals(command.getDescription()))
                .filter(recipeIngredients -> recipeIngredients.getAmount().equals(command.getAmount()))
                .filter(recipeIngredients -> recipeIngredients.getUnitOfMeasure().getId().equals(command.getUnitOfMeasure().getId()))
                .findFirst();
    }

    private void updateIngredient(Ingredient ingredientFound, IngredientCommand command) {
        ingredientFound.setDescription(command.getDescription());
        ingredientFound.setAmount(command.getAmount());
        ingredientFound.setUnitOfMeasure(unitOfMeasureRepository
                .findById(command.getUnitOfMeasure().getId())
                .orElseThrow(() -> new RuntimeException("UnitOfMeasure NOT FOUND for ingredient.id = " + ingredientFound.getId())));
    }

    private Recipe checkIfRecipeExists(IngredientCommand command) {
        //make sure we have expected recipe
        Optional<Recipe> recipeOptional = recipeRepository.findById(command.getRecipeId());

        if (recipeOptional.isEmpty()) {
            throw new RuntimeException("Recipe not found for id: " + command.getRecipeId());
        }
        return recipeOptional.get();
    }

    @Override
    public void deleteById(Long id) {
        ingredientRepository.deleteById(id);
    }

}
