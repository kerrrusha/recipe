package com.kerrrusha.recipe.service.impl;

import com.kerrrusha.recipe.command.RecipeCommand;
import com.kerrrusha.recipe.converter.recipe.RecipeCommandToRecipeConverter;
import com.kerrrusha.recipe.converter.recipe.RecipeToRecipeCommandConverter;
import com.kerrrusha.recipe.model.Recipe;
import com.kerrrusha.recipe.repository.RecipeRepository;
import com.kerrrusha.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeCommandToRecipeConverter recipeCommandToRecipeConverter;
    private final RecipeToRecipeCommandConverter recipeToRecipeCommandConverter;

    @Override
    public Set<Recipe> findAll() {
        Set<Recipe> result = new HashSet<>();
        recipeRepository.findAll().iterator().forEachRemaining(result::add);
        return result;
    }

    @Override
    public Set<RecipeCommand> findAllCommands() {
        return findAll().stream()
                .map(recipeToRecipeCommandConverter::convert)
                .collect(Collectors.toSet());
    }

    @Override
    public Recipe findById(Long id) {
        return recipeRepository.findById(id).orElseThrow(() -> new RuntimeException("Recipe was not found by given id: " + id));
    }

    @Override
    @Transactional
    public RecipeCommand findCommandById(Long id) {
        return recipeToRecipeCommandConverter.convert(findById(id));
    }

    @Override
    @Transactional
    public RecipeCommand saveRecipeCommand(RecipeCommand command) {
        Recipe detachedRecipe = recipeCommandToRecipeConverter.convert(command);

        assert detachedRecipe != null;
        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved RecipeId: " + savedRecipe.getId());
        return recipeToRecipeCommandConverter.convert(savedRecipe);
    }

    @Override
    public void deleteById(Long id) {
        recipeRepository.deleteById(id);
    }

}
