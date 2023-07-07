package com.kerrrusha.recipe.service;

import com.kerrrusha.recipe.command.RecipeCommand;
import com.kerrrusha.recipe.model.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> findAll();

    Recipe findById(Long id);

    RecipeCommand findCommandById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand command);

}
