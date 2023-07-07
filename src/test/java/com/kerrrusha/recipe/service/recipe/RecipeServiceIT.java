package com.kerrrusha.recipe.service.recipe;

import com.kerrrusha.recipe.command.RecipeCommand;
import com.kerrrusha.recipe.converter.recipe.RecipeCommandToRecipeConverter;
import com.kerrrusha.recipe.converter.recipe.RecipeToRecipeCommandConverter;
import com.kerrrusha.recipe.model.Recipe;
import com.kerrrusha.recipe.repository.RecipeRepository;
import com.kerrrusha.recipe.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class RecipeServiceIT {

    private static final String NEW_DESCRIPTION = "New Description";

    @Autowired
    RecipeService service;

    @Autowired
    RecipeRepository repository;

    @Autowired
    RecipeCommandToRecipeConverter recipeCommandToRecipeConverter;

    @Autowired
    RecipeToRecipeCommandConverter recipeToRecipeCommandConverter;

    @Test
    @Transactional
    void testSaveOfDescription() {
        //given
        Iterable<Recipe> recipes = repository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeCommand testRecipeCommand = recipeToRecipeCommandConverter.convert(testRecipe);

        //when
        testRecipeCommand.setDescription(NEW_DESCRIPTION);
        RecipeCommand savedRecipeCommand = service.saveRecipeCommand(testRecipeCommand);

        //then
        assertEquals(NEW_DESCRIPTION, savedRecipeCommand.getDescription());
        assertEquals(testRecipe.getId(), savedRecipeCommand.getId());
        assertEquals(testRecipe.getCategories().size(), savedRecipeCommand.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(), savedRecipeCommand.getIngredients().size());
    }

}
