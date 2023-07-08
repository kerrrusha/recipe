package com.kerrrusha.recipe.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UnitOfMeasure extends BaseEntity {

    private String name;

    @Override
    public String toString() {
        return "UnitOfMeasure{" +
                "name='" + name + '\'' +
                '}';
    }

}
