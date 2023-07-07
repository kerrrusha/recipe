package com.kerrrusha.recipe.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public class Recipe extends BaseEntity {

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servingsMin;
    private Integer servingsMax;
    private String url;

    @Lob
    private String directions;

    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty = Difficulty.NONE;

    @Lob
    private Byte[] image;

    @OneToOne(cascade = CascadeType.ALL)
    private Notes notes;

    @ManyToMany
    @JoinTable(name = "recipe_category",
                joinColumns = @JoinColumn(name = "recipe_id"),
                inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    public void setNotes(Notes notes) {
        this.notes = notes;
        if (nonNull(notes)) {
            notes.setRecipe(this);
        }
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = isNull(difficulty) ? Difficulty.NONE : difficulty;
    }

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
        if (nonNull(ingredient)) {
            ingredient.setRecipe(this);
        }
    }

}
