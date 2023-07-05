package com.kerrrusha.recipe.service;

import com.kerrrusha.recipe.model.Recipe;

public interface RecipeService {

    Iterable<Recipe> findAll();

    Recipe findById(Long id);

}
