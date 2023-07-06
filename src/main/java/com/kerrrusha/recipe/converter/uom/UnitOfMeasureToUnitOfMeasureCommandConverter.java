package com.kerrrusha.recipe.converter.uom;

import com.kerrrusha.recipe.command.UnitOfMeasureCommand;
import com.kerrrusha.recipe.model.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class UnitOfMeasureToUnitOfMeasureCommandConverter implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Nullable
    @Override
    @Synchronized
    public UnitOfMeasureCommand convert(@Nullable UnitOfMeasure unitOfMeasure) {
        if (isNull(unitOfMeasure)) {
            return null;
        }

        final UnitOfMeasureCommand uomc = new UnitOfMeasureCommand();
        uomc.setId(unitOfMeasure.getId());
        uomc.setName(unitOfMeasure.getName());
        return uomc;
    }

}
