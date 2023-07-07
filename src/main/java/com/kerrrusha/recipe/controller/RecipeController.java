package com.kerrrusha.recipe.controller;

import com.kerrrusha.recipe.command.RecipeCommand;
import com.kerrrusha.recipe.service.RecipeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequestMapping("/recipe")
@RequiredArgsConstructor
public class RecipeController {

    private final RecipeService recipeService;

    @GetMapping("/{id}/show")
    public String get(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(id));
        return "recipe/show";
    }

    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("recipe", new RecipeCommand());
        return "recipe/create-or-update";
    }

    @GetMapping("/{id}/update")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(id));
        return "recipe/create-or-update";
    }

    @PostMapping
    public String saveOrUpdate(@ModelAttribute RecipeCommand command) {
        RecipeCommand savedCommand = recipeService.saveRecipeCommand(command);
        return "redirect:/recipe/" + savedCommand.getId() + "/show/";
    }

    @GetMapping("/{id}/delete")
    public String delete(@PathVariable Long id) {
        log.debug("Deleting recipe with id = {}", id);
        recipeService.deleteById(id);
        return "redirect:/";
    }

}
