package com.kerrrusha.recipe.controller;

import com.kerrrusha.recipe.command.IngredientCommand;
import com.kerrrusha.recipe.service.IngredientService;
import com.kerrrusha.recipe.service.RecipeService;
import com.kerrrusha.recipe.service.UnitOfMeasureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/recipe")
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    @GetMapping("/{recipeId}/ingredients")
    public String getRecipeIngredients(@PathVariable Long recipeId, Model model){
        log.debug("Getting ingredient list for recipe id: " + recipeId);

        // use command object to avoid lazy load errors in Thymeleaf.
        model.addAttribute("recipe", recipeService.findCommandById(recipeId));

        return "recipe/ingredient/list";
    }

    @GetMapping("/{recipeId}/ingredient/{id}/show")
    public String get(@PathVariable Long recipeId, @PathVariable Long id, Model model) {
        model.addAttribute("ingredient", ingredientService.findCommandById(id));
        return "recipe/ingredient/show";
    }

    @GetMapping("/{recipeId}/ingredient/{id}/update")
    public String update(@PathVariable String recipeId, @PathVariable Long id, Model model) {
        model.addAttribute("ingredient", ingredientService.findCommandById(id));
        model.addAttribute("uomList", unitOfMeasureService.findAllCommands());
        return "recipe/ingredient/create-or-update";
    }

    @PostMapping("/{recipeId}/ingredient")
    public String saveOrUpdate(@ModelAttribute IngredientCommand command) {
        IngredientCommand savedCommand = ingredientService.saveIngredientCommand(command);

        log.debug("Saved recipe id: " + savedCommand.getRecipeId());
        log.debug("Saved ingredient id :" + savedCommand.getId());

        return "redirect:/recipe/" + savedCommand.getRecipeId() + "/ingredient/" + savedCommand.getId() + "/show";
    }

    @GetMapping("/{recipeId}/ingredient/{id}/delete")
    public String delete(@PathVariable String recipeId, @PathVariable Long id) {
        log.debug("Deleting ingredient with id = {}", id);
        ingredientService.deleteById(id);
        return "redirect:/";
    }

}
