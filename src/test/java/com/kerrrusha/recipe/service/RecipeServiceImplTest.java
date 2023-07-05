package com.kerrrusha.recipe.service;

import com.kerrrusha.recipe.model.Recipe;
import com.kerrrusha.recipe.repository.RecipeRepository;
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

    @BeforeEach
    void setUp() {
        service = new RecipeServiceImpl(recipeRepository);
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

}