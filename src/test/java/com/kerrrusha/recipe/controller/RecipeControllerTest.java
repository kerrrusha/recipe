package com.kerrrusha.recipe.controller;

import com.kerrrusha.recipe.command.RecipeCommand;
import com.kerrrusha.recipe.model.Recipe;
import com.kerrrusha.recipe.service.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class RecipeControllerTest {

    @Mock
    RecipeService service;

    @InjectMocks
    RecipeController controller;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void showById() throws Exception {
        Recipe recipe = Recipe.builder().id(1L).build();
        when(service.findById(anyLong())).thenReturn(recipe);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"));
        verify(service, times(1)).findById(anyLong());
        verify(service, never()).findAll();
    }

    @Test
    public void get() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/create"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/create"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    public void create() throws Exception {
        RecipeCommand command = RecipeCommand.builder().id(2L).build();

        when(service.saveRecipeCommand(any())).thenReturn(command);

        mockMvc.perform(post("/recipe")
                                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/show/"));
    }

    @Test
    public void update() throws Exception {
        RecipeCommand command = RecipeCommand.builder().id(2L).build();

        when(service.findCommandById(anyLong())).thenReturn(command);

        mockMvc.perform(MockMvcRequestBuilders.get("/recipe/1/update"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/create"))
                .andExpect(model().attributeExists("recipe"));
    }

}
