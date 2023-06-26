package com.kerrrusha.recipe.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Notes extends BaseEntity {

    private String notes;

    @Override
    public String toString() {
        return "Notes{" +
                "notes='" + notes + '\'' +
                '}';
    }

}
