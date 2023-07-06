package com.kerrrusha.recipe.converter.uom;

import com.kerrrusha.recipe.command.UnitOfMeasureCommand;
import com.kerrrusha.recipe.model.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import static java.util.Objects.isNull;

@Component
public class UnitOfMeasureCommandToUnitOfMeasureConverter implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

    @Nullable
    @Override
    @Synchronized
    public UnitOfMeasure convert(@Nullable UnitOfMeasureCommand source) {
        if (isNull(source)) {
            return null;
        }

        final UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(source.getId());
        uom.setName(source.getName());
        return uom;
    }

}
