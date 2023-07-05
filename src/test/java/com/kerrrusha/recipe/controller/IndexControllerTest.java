package com.kerrrusha.recipe.controller;

import com.kerrrusha.recipe.model.Recipe;
import com.kerrrusha.recipe.service.RecipeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@ExtendWith(MockitoExtension.class)
class IndexControllerTest {

    @Mock
    RecipeService service;

    @Mock
    Model model;

    @InjectMocks
    IndexController controller;

    @Test
    void testMockMVC() throws Exception {
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void index() {
        //given
        Set<Recipe> givenSet = Set.of(
                Recipe.builder().id(1L).description("Tasty stuff").build(),
                Recipe.builder().id(2L).description("Yum yum").build()
        );
        when(service.findAll()).thenReturn(givenSet);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        //when
        String viewName = controller.index(model);

        //then
        assertEquals("index", viewName);
        verify(service, times(1)).findAll();
        verify(model, times(1)).addAttribute(eq("recipes"), argumentCaptor.capture());

        Set<Recipe> setInController = argumentCaptor.getValue();
        assertEquals(2, setInController.size());
    }

}
