package com.kerrrusha.recipe.repository;

import com.kerrrusha.recipe.model.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
