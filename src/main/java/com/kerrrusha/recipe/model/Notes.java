package com.kerrrusha.recipe.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class Notes extends AbstractPersistable<Long> {

    private String notes;

}
