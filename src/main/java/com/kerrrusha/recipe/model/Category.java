package com.kerrrusha.recipe.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Getter
@Setter
public class Category extends AbstractPersistable<Long> {

    private String name;

    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

}
