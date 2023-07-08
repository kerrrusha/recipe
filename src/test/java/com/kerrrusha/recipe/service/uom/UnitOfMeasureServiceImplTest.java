package com.kerrrusha.recipe.service.uom;

import com.kerrrusha.recipe.command.UnitOfMeasureCommand;
import com.kerrrusha.recipe.converter.uom.UnitOfMeasureToUnitOfMeasureCommandConverter;
import com.kerrrusha.recipe.model.UnitOfMeasure;
import com.kerrrusha.recipe.repository.UnitOfMeasureRepository;
import com.kerrrusha.recipe.service.UnitOfMeasureService;
import com.kerrrusha.recipe.service.impl.UnitOfMeasureServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UnitOfMeasureServiceImplTest {

    UnitOfMeasureToUnitOfMeasureCommandConverter unitOfMeasureToUnitOfMeasureCommandConverter = new UnitOfMeasureToUnitOfMeasureCommandConverter();

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    UnitOfMeasureService service;

    @BeforeEach
    public void setUp() throws Exception {
        service = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, unitOfMeasureToUnitOfMeasureCommandConverter);
    }

    @Test
    public void findAllCommands() {
        //given
        Set<UnitOfMeasure> unitOfMeasures = Set.of(
                UnitOfMeasure.builder().id(1L).build(),
                UnitOfMeasure.builder().id(2L).build()
        );

        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);

        //when
        Set<UnitOfMeasureCommand> commands = service.findAllCommands();

        //then
        assertEquals(2, commands.size());
        verify(unitOfMeasureRepository, times(1)).findAll();
    }

}
