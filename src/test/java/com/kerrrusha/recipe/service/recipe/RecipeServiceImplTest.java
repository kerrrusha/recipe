package com.kerrrusha.recipe.service.recipe;

import com.kerrrusha.recipe.command.RecipeCommand;
import com.kerrrusha.recipe.converter.category.CategoryCommandToCategoryConverter;
import com.kerrrusha.recipe.converter.category.CategoryToCategoryCommandConverter;
import com.kerrrusha.recipe.converter.ingredient.IngredientCommandToIngredientConverter;
import com.kerrrusha.recipe.converter.ingredient.IngredientToIngredientCommandConverter;
import com.kerrrusha.recipe.converter.notes.NotesCommandToNotesConverter;
import com.kerrrusha.recipe.converter.notes.NotesToNotesCommandConverter;
import com.kerrrusha.recipe.converter.recipe.RecipeCommandToRecipeConverter;
import com.kerrrusha.recipe.converter.recipe.RecipeToRecipeCommandConverter;
import com.kerrrusha.recipe.converter.uom.UnitOfMeasureCommandToUnitOfMeasureConverter;
import com.kerrrusha.recipe.converter.uom.UnitOfMeasureToUnitOfMeasureCommandConverter;
import com.kerrrusha.recipe.model.Recipe;
import com.kerrrusha.recipe.repository.RecipeRepository;
import com.kerrrusha.recipe.service.impl.RecipeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceImplTest {

    RecipeServiceImpl service;

    @Mock
    RecipeRepository recipeRepository;

    RecipeCommandToRecipeConverter recipeCommandToRecipeConverter = new RecipeCommandToRecipeConverter(
            new CategoryCommandToCategoryConverter(),
            new IngredientCommandToIngredientConverter(new UnitOfMeasureCommandToUnitOfMeasureConverter()),
            new NotesCommandToNotesConverter()
    );

    RecipeToRecipeCommandConverter recipeToRecipeCommandConverter = new RecipeToRecipeCommandConverter(
            new CategoryToCategoryCommandConverter(),
            new IngredientToIngredientCommandConverter(new UnitOfMeasureToUnitOfMeasureCommandConverter()),
            new NotesToNotesCommandConverter()
    );

    @BeforeEach
    void setUp() {
        service = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipeConverter, recipeToRecipeCommandConverter);
    }

    @Test
    void findAll() {
        Set<Recipe> givenSet = Set.of(
                Recipe.builder().id(1L).description("Tasty stuff").build(),
                Recipe.builder().id(2L).description("Yum yum").build()
        );
        when(recipeRepository.findAll()).thenReturn(givenSet);

        Set<Recipe> result = service.findAll();

        assertEquals(2, result.size());
        verify(recipeRepository, times(1)).findAll();
        verify(recipeRepository, never()).findById(anyLong());
    }

    @Test
    void findById() {
        Optional<Recipe> givenRecipe = Optional.of(Recipe.builder().id(1L).description("Tasty stuff").build());
        when(recipeRepository.findById(anyLong())).thenReturn(givenRecipe);

        Recipe result = service.findById(1L);

        assertNotNull(result);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    void findRecipeCommandById() {
        Recipe recipe = Recipe.builder().id(1L).build();
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        RecipeCommand commandById = service.findCommandById(1L);

        assertNotNull(commandById);
        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    void deleteById() {
        service.deleteById(2L);

        verify(recipeRepository, times(1)).deleteById(anyLong());
    }

}