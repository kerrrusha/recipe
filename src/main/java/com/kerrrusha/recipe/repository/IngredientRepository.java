package com.kerrrusha.recipe.repository;

import com.kerrrusha.recipe.model.Ingredient;
import org.springframework.data.repository.CrudRepository;

public interface IngredientRepository extends CrudRepository<Ingredient, Long> {
}
