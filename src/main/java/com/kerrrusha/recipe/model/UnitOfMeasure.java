package com.kerrrusha.recipe.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class UnitOfMeasure extends AbstractPersistable<Long> {

    private String name;

}
