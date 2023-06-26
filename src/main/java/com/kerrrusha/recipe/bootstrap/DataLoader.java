package com.kerrrusha.recipe.bootstrap;

import com.kerrrusha.recipe.model.*;
import com.kerrrusha.recipe.repository.CategoryRepository;
import com.kerrrusha.recipe.repository.RecipeRepository;
import com.kerrrusha.recipe.repository.UnitOfMeasureRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        recipeRepository.save(createRecipe());
    }

    private Recipe createRecipe() {
        Recipe guacamole = new Recipe();

        guacamole.setPrepTime(10);
        guacamole.setCookTime(10);
        guacamole.setServingsMin(2);
        guacamole.setServingsMax(4);
        guacamole.setDirections("""
                Cut the avocados:
                Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl.

                How to make guacamole - scoring avocado
                Elise Bauer
                Mash the avocado flesh:
                Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)

                How to make guacamole - smashing avocado with fork
                Elise Bauer
                Add the remaining ingredients to taste:
                Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.

                Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat.

                Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.

                Serve immediately:
                If making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.)

                Garnish with slices of red radish or jigama strips. Serve with your choice of store-bought tortilla chips or make your own homemade tortilla chips.

                Refrigerate leftover guacamole up to 3 days.

                Note: Chilling tomatoes hurts their flavor. So, if you want to add chopped tomato to your guacamole, add it just before serving.""");

        Notes guacamoleNotes = new Notes();
        guacamoleNotes.setNotes("Be careful handling chilis! If using, it's best to wear food-safe gloves. If no gloves are available, wash your hands thoroughly after handling, and do not touch your eyes or the area near your eyes for several hours afterwards.");
        guacamole.setNotes(guacamoleNotes);
        guacamole.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/");
        guacamole.setDescription("The best guacamole keeps it simple: just ripe avocados and a handful of flavorful mix-ins. Serve it as a dip at your next party or spoon it on top of tacos for an easy dinner upgrade.");
        guacamole.setDifficulty(Difficulty.MODERATE);

        Category category1 = new Category();
        category1.setName("GAME DAY");

        Category category2 = new Category();
        category2.setName("CINCO DE MAYO\n");

        Category category3 = new Category();
        category3.setName("4TH OF JULY\n");

        Category category4 = new Category();
        category4.setName("SUPER BOWL\n");

        Category category5 = new Category();
        category5.setName("GLUTEN-FREE");

        Set<Category> categories = Set.of(category1, category2, category3, category4, category5);
        categoryRepository.saveAll(categories);

        guacamole.setCategories(categories);

        UnitOfMeasure piece = new UnitOfMeasure();
        piece.setName("piece");
        unitOfMeasureRepository.save(piece);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setDescription("avocado");
        ingredient1.setAmount(BigDecimal.valueOf(2));
        ingredient1.setUnitOfMeasure(piece);
        ingredient1.setRecipe(guacamole);

        UnitOfMeasure teaspoon = new UnitOfMeasure();
        teaspoon.setName("teaspoon");
        unitOfMeasureRepository.save(teaspoon);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setDescription("kosher salt, plus more to taste");
        ingredient2.setAmount(BigDecimal.valueOf(1/4.0));
        ingredient2.setUnitOfMeasure(teaspoon);
        ingredient2.setRecipe(guacamole);

        guacamole.setIngredients(Set.of(ingredient1, ingredient2));

        return guacamole;
    }

}
