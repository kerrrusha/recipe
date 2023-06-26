package com.kerrrusha.recipe.repository;

import com.kerrrusha.recipe.model.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
