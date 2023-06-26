package com.kerrrusha.recipe.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class UnitOfMeasure extends BaseEntity {

    private String name;

    @Override
    public String toString() {
        return "UnitOfMeasure{" +
                "name='" + name + '\'' +
                '}';
    }

}
