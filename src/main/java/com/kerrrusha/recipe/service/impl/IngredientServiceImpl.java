package com.kerrrusha.recipe.service.impl;

import com.kerrrusha.recipe.command.IngredientCommand;
import com.kerrrusha.recipe.converter.ingredient.IngredientCommandToIngredientConverter;
import com.kerrrusha.recipe.converter.ingredient.IngredientToIngredientCommandConverter;
import com.kerrrusha.recipe.model.Ingredient;
import com.kerrrusha.recipe.repository.IngredientRepository;
import com.kerrrusha.recipe.service.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {

    private final IngredientRepository repository;
    private final IngredientCommandToIngredientConverter ingredientCommandToIngredientConverter;
    private final IngredientToIngredientCommandConverter ingredientToIngredientCommandConverter;

    @Override
    public Set<IngredientCommand> findAllCommands() {
        Set<IngredientCommand> result = new HashSet<>();
        repository.findAll().iterator().forEachRemaining(e -> result.add(ingredientToIngredientCommandConverter.convert(e)));
        return result;
    }

    @Override
    public IngredientCommand findCommandById(Long id) {
        return ingredientToIngredientCommandConverter.convert(findById(id));
    }

    private Ingredient findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Ingredient was not found by given id: " + id));
    }

    @Override
    public IngredientCommand saveRecipeCommand(IngredientCommand command) {
        Ingredient detached = ingredientCommandToIngredientConverter.convert(command);

        assert detached != null;
        Ingredient saved = repository.save(detached);
        return ingredientToIngredientCommandConverter.convert(saved);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

}
