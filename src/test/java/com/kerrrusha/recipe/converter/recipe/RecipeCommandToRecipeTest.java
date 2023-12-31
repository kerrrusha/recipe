package com.kerrrusha.recipe.converter.recipe;

import com.kerrrusha.recipe.command.CategoryCommand;
import com.kerrrusha.recipe.command.IngredientCommand;
import com.kerrrusha.recipe.command.NotesCommand;
import com.kerrrusha.recipe.command.RecipeCommand;
import com.kerrrusha.recipe.converter.category.CategoryCommandToCategoryConverter;
import com.kerrrusha.recipe.converter.ingredient.IngredientCommandToIngredientConverter;
import com.kerrrusha.recipe.converter.notes.NotesCommandToNotesConverter;
import com.kerrrusha.recipe.converter.uom.UnitOfMeasureCommandToUnitOfMeasureConverter;
import com.kerrrusha.recipe.model.Difficulty;
import com.kerrrusha.recipe.model.Recipe;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RecipeCommandToRecipeTest {

    public static final Long RECIPE_ID = 1L;
    public static final Integer COOK_TIME = 5;
    public static final Integer PREP_TIME = 7;
    public static final String DESCRIPTION = "My Recipe";
    public static final String DIRECTIONS = "Directions";
    public static final Difficulty DIFFICULTY = Difficulty.EASY;
    public static final Integer SERVINGS_MIN = 3;
    public static final Integer SERVINGS_MAX = 4;
    public static final String URL = "Some URL";
    public static final Long CAT_ID_1 = 1L;
    public static final Long CAT_ID2 = 2L;
    public static final Long INGRED_ID_1 = 3L;
    public static final Long INGRED_ID_2 = 4L;
    public static final Long NOTES_ID = 9L;

    RecipeCommandToRecipeConverter converter;

    @BeforeEach
    public void setUp() throws Exception {
        converter = new RecipeCommandToRecipeConverter(new CategoryCommandToCategoryConverter(),
                new IngredientCommandToIngredientConverter(new UnitOfMeasureCommandToUnitOfMeasureConverter()),
                new NotesCommandToNotesConverter());
    }

    @Test
    public void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    public void testEmptyObject() {
        assertNotNull(converter.convert(new RecipeCommand()));
    }

    @Test
    public void convert() throws Exception {
        //given
        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(RECIPE_ID);
        recipeCommand.setCookTime(COOK_TIME);
        recipeCommand.setPrepTime(PREP_TIME);
        recipeCommand.setDescription(DESCRIPTION);
        recipeCommand.setDifficulty(DIFFICULTY);
        recipeCommand.setDirections(DIRECTIONS);
        recipeCommand.setServingsMin(SERVINGS_MIN);
        recipeCommand.setServingsMax(SERVINGS_MAX);
        recipeCommand.setUrl(URL);

        NotesCommand notes = new NotesCommand();
        notes.setId(NOTES_ID);

        recipeCommand.setNotes(notes);

        CategoryCommand category = new CategoryCommand();
        category.setId(CAT_ID_1);

        CategoryCommand category2 = new CategoryCommand();
        category2.setId(CAT_ID2);

        recipeCommand.getCategories().add(category);
        recipeCommand.getCategories().add(category2);

        IngredientCommand ingredient = new IngredientCommand();
        ingredient.setId(INGRED_ID_1);

        IngredientCommand ingredient2 = new IngredientCommand();
        ingredient2.setId(INGRED_ID_2);

        recipeCommand.getIngredients().add(ingredient);
        recipeCommand.getIngredients().add(ingredient2);

        //when
        Recipe recipe = converter.convert(recipeCommand);

        assertNotNull(recipe);
        assertEquals(RECIPE_ID, recipe.getId());
        assertEquals(COOK_TIME, recipe.getCookTime());
        assertEquals(PREP_TIME, recipe.getPrepTime());
        assertEquals(DESCRIPTION, recipe.getDescription());
        assertEquals(DIFFICULTY, recipe.getDifficulty());
        assertEquals(DIRECTIONS, recipe.getDirections());
        assertEquals(SERVINGS_MIN, recipe.getServingsMin());
        assertEquals(SERVINGS_MAX, recipe.getServingsMax());
        assertEquals(URL, recipe.getUrl());
        assertEquals(NOTES_ID, recipe.getNotes().getId());
        assertEquals(2, recipe.getCategories().size());
        assertEquals(2, recipe.getIngredients().size());
    }

}