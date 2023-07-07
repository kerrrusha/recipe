package com.kerrrusha.recipe.service;

import com.kerrrusha.recipe.command.IngredientCommand;

import java.util.Set;

public interface IngredientService {

    Set<IngredientCommand> findAllCommands();

    IngredientCommand findCommandById(Long id);

    IngredientCommand saveRecipeCommand(IngredientCommand command);

    void deleteById(Long id);

}
