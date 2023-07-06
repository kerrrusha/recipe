package com.kerrrusha.recipe.service;

import com.kerrrusha.recipe.command.RecipeCommand;
import com.kerrrusha.recipe.model.Recipe;

public interface RecipeService {

    Iterable<Recipe> findAll();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

}
