package com.kerrrusha.recipe.service;

import com.kerrrusha.recipe.command.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> findAllCommands();

}
