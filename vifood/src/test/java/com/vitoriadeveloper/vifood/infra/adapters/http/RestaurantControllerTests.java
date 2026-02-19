package com.vitoriadeveloper.vifood.infra.adapters.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitoriadeveloper.vifood.application.services.RestaurantService;
import com.vitoriadeveloper.vifood.domain.exceptions.RestaurantNotFoundException;
import com.vitoriadeveloper.vifood.domain.model.Kitchen;
import com.vitoriadeveloper.vifood.domain.model.Restaurant;
import com.vitoriadeveloper.vifood.infra.exceptions.ApiExceptionHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RestaurantController.class)
@Import(ApiExceptionHandler.class)
public class RestaurantControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private RestaurantService service;

    @Test
    @DisplayName("Deve ser possível criar um restaurante com sucesso")
    void shouldCreateRstaurant() throws Exception {
        populateRestaurant();

        when(service.create(any(Restaurant.class))).thenReturn(populateRestaurant());

        mockMvc.perform(post("/restaurantes")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(populateRestaurant())))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Vifood"))
                .andExpect(jsonPath("$.aberto").value(true))
                .andExpect(jsonPath("$.ativo").value(true))
                .andExpect(jsonPath("$.taxaFrete").value(2.0));
    }

    @Test
    @DisplayName("Deve retornar erro ao criar restaurante sem nome")
    void shouldReturnErrorWhenCreatingRestaurantWithoutName() throws Exception {
        Restaurant restaurant = new Restaurant();
        restaurant.setAberto(true);
        restaurant.setAtivo(true);
        restaurant.setTaxaFrete(BigDecimal.valueOf(2.0));

        Kitchen kitchen = new Kitchen();
        kitchen.setId(1L);

        restaurant.setCozinha(kitchen);

        mockMvc.perform(post("/restaurantes")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(restaurant)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fields[0].name").value("nome"))
                .andExpect(jsonPath("$.fields[0].userMessage").value("nome é obrigatório."));
    }

    @Test
    @DisplayName("Deve ser possível listar os restaurantes")
    void shouldListRestaurants() throws Exception {
        populateRestaurant();

        when(service.findAll()).thenReturn(List.of(populateRestaurant()));

        mockMvc.perform(get("/restaurantes")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Vifood"))
                .andExpect(jsonPath("$[0].id").value(2L));
    }

    @Test
    @DisplayName("Deve ser possível achar restaurante por id")
    void shouldFindRestaurantById() throws Exception {
        populateRestaurant();

        when(service.findById(2L)).thenReturn(populateRestaurant());

        mockMvc.perform(get("/restaurantes/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Vifood"))
                .andExpect(jsonPath("$.id").value(2L));
    }

    @Test
    @DisplayName("Deve retornar erro ao deletar um restaurante por id inexistente")
    void shouldDeleteRestaurantById() throws Exception {
        doThrow(new RestaurantNotFoundException(3L))
                .when(service)
                .deleteById(3L);

        mockMvc.perform(delete("/restaurantes/3")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message").value("Restaurante de id: 3 não encontrado!"));
    }

    @Test
    @DisplayName("Deve ser possível atualizar parcialmente um restaurante")
    void shouldUpdatePartialRestaurant() throws Exception {
        populateRestaurant();

        when(service.findById(2L)).thenReturn(populateRestaurant());

        mockMvc.perform(patch("/restaurantes/2")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"nome\": \"Vifood Updated\"}"))
                .andExpect(status().isNoContent());
    }

    @Test
    @DisplayName("Deve ser possível deletar um restaurante por id")
    void shouldDeleteRestaurantByIdSuccess() throws Exception {
        mockMvc.perform(delete("/restaurantes/2")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    private Restaurant populateRestaurant() {
        Restaurant restaurant = new Restaurant();
        restaurant.setId(2L);
        restaurant.setNome("Vifood");
        restaurant.setAberto(true);
        restaurant.setAtivo(true);
        restaurant.setTaxaFrete(BigDecimal.valueOf(2.0));

        Kitchen kitchen = new Kitchen();
        kitchen.setId(1L);

        restaurant.setCozinha(kitchen);
        return restaurant;
    }
}
