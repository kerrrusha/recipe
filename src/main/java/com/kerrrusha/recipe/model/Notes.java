package com.kerrrusha.recipe.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.OneToOne;

@Entity
@Getter
@Setter
public class Notes extends BaseEntity {

    @Lob
    private String notes;

    @OneToOne
    private Recipe recipe;

    @Override
    public String toString() {
        return "Notes{" +
                "notes='" + notes + '\'' +
                '}';
    }

}
