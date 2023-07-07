package com.kerrrusha.recipe.service.ingredient;

import com.kerrrusha.recipe.command.IngredientCommand;
import com.kerrrusha.recipe.converter.ingredient.IngredientCommandToIngredientConverter;
import com.kerrrusha.recipe.converter.ingredient.IngredientToIngredientCommandConverter;
import com.kerrrusha.recipe.converter.uom.UnitOfMeasureCommandToUnitOfMeasureConverter;
import com.kerrrusha.recipe.converter.uom.UnitOfMeasureToUnitOfMeasureCommandConverter;
import com.kerrrusha.recipe.model.Ingredient;
import com.kerrrusha.recipe.repository.IngredientRepository;
import com.kerrrusha.recipe.service.impl.IngredientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class IngredientServiceImplTest {

    IngredientServiceImpl service;

    @Mock
    IngredientRepository ingredientRepository;

    IngredientCommandToIngredientConverter ingredientCommandToIngredientConverter =
            new IngredientCommandToIngredientConverter(new UnitOfMeasureCommandToUnitOfMeasureConverter());

    IngredientToIngredientCommandConverter ingredientToIngredientCommandConverter =
            new IngredientToIngredientCommandConverter(new UnitOfMeasureToUnitOfMeasureCommandConverter());

    @BeforeEach
    void setUp() {
        service = new IngredientServiceImpl(ingredientRepository, ingredientCommandToIngredientConverter, ingredientToIngredientCommandConverter);
    }

    @Test
    void findAllCommands() {
        Set<Ingredient> givenSet = Set.of(
                Ingredient.builder().id(1L).description("Tasty stuff").build(),
                Ingredient.builder().id(2L).description("Yum yum").build()
        );
        when(ingredientRepository.findAll()).thenReturn(givenSet);

        Set<IngredientCommand> result = service.findAllCommands();

        assertEquals(2, result.size());
        verify(ingredientRepository, times(1)).findAll();
        verify(ingredientRepository, never()).findById(anyLong());
    }

    @Test
    void findCommandById() {
        Ingredient recipe = Ingredient.builder().id(1L).build();
        Optional<Ingredient> recipeOptional = Optional.of(recipe);

        when(ingredientRepository.findById(anyLong())).thenReturn(recipeOptional);

        IngredientCommand commandById = service.findCommandById(1L);

        assertNotNull(commandById);
        verify(ingredientRepository, times(1)).findById(anyLong());
        verify(ingredientRepository, never()).findAll();
    }

    @Test
    void deleteById() {
        service.deleteById(2L);

        verify(ingredientRepository, times(1)).deleteById(anyLong());
    }

}
