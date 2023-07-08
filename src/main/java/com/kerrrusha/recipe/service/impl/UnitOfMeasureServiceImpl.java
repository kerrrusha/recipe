package com.kerrrusha.recipe.service.impl;

import com.kerrrusha.recipe.command.UnitOfMeasureCommand;
import com.kerrrusha.recipe.converter.uom.UnitOfMeasureToUnitOfMeasureCommandConverter;
import com.kerrrusha.recipe.repository.UnitOfMeasureRepository;
import com.kerrrusha.recipe.service.UnitOfMeasureService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository unitOfMeasureRepository;
    private final UnitOfMeasureToUnitOfMeasureCommandConverter unitOfMeasureToUnitOfMeasureCommand;

    @Override
    public Set<UnitOfMeasureCommand> findAllCommands() {
        return StreamSupport.stream(unitOfMeasureRepository.findAll().spliterator(), false)
                .map(unitOfMeasureToUnitOfMeasureCommand::convert)
                .collect(Collectors.toSet());
    }

}
