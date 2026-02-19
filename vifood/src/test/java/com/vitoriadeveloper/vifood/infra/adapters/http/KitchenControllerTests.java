package com.vitoriadeveloper.vifood.infra.adapters.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vitoriadeveloper.vifood.application.services.KitchenService;
import com.vitoriadeveloper.vifood.domain.exceptions.KitchenNotFoundException;
import com.vitoriadeveloper.vifood.domain.model.Kitchen;
import com.vitoriadeveloper.vifood.infra.exceptions.ApiExceptionHandler;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(KitchenController.class)
@Import(ApiExceptionHandler.class)
class KitchenControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private KitchenService service;

    @Test
    @DisplayName("Deve criar uma cozinha com sucesso")
    void shouldCreateKitchen() throws Exception {

        Kitchen kitchen = new Kitchen();
        kitchen.setNome("Japonesa");

        when(service.create(any(Kitchen.class))).thenReturn(kitchen);

        mockMvc.perform(post("/cozinhas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(kitchen)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Japonesa"));
    }

    @Test
    @DisplayName("Deve retornar erro ao criar cozinha sem nome")
    void shouldReturnErrorWhenCreatingKitchenWithoutName() throws Exception {
        Kitchen kitchen = new Kitchen();
        kitchen.setNome("");

        mockMvc.perform(post("/cozinhas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(kitchen)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.fields[0].name").value("nome"))
                .andExpect(jsonPath("$.fields[0].userMessage").value("nome é obrigatório."));
    }

    @Test
    @DisplayName("Deve achar cozinha por id")
    void shouldFindKitchenById() throws Exception {
        Kitchen kitchen = new Kitchen();
        kitchen.setId(1L);
        kitchen.setNome("Italiana");

        when(service.findById(1L)).thenReturn(kitchen);

        mockMvc.perform(get("/cozinhas/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Italiana"));
    }

    @Test
    @DisplayName("Deve ser possível listar as cozinhas")
    void shouldListKitchens() throws Exception {
        Kitchen kitchen1 = new Kitchen();
        kitchen1.setId(1L);
        kitchen1.setNome("Italiana");

        Kitchen kitchen2 = new Kitchen();
        kitchen2.setId(2L);
        kitchen2.setNome("Japonesa");

        when(service.findAll()).thenReturn(List.of(kitchen1, kitchen2));

        mockMvc.perform(get("/cozinhas")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nome").value("Italiana"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].nome").value("Japonesa"));
    }

    @Test
    @DisplayName("Deve ser possível atualizar uma cozinha")
    void shouldUpdateKitchen() throws Exception {
        Kitchen kitchenResponse = new Kitchen();
        kitchenResponse.setId(1L);
        kitchenResponse.setNome("Brasileira");

        when(service.updateById(eq(1L), any(Kitchen.class)))
                .thenReturn(kitchenResponse);

        mockMvc.perform(put("/cozinhas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                             {
                                 "nome": "Brasileira"
                             }
                             """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Brasileira"));
    }

    @Test
    @DisplayName("Deve ser possível deletar uma cozinha")
    void shouldDeleteKitchen() throws Exception {
        mockMvc.perform(delete("/cozinhas/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Deve retornar erro ao deletar cozinha inexistente")
    void shouldReturnErrorWhenDeletingNonExistingKitchen() throws Exception {

        doThrow(new KitchenNotFoundException(1L))
                .when(service)
                .deleteById(1L);

        mockMvc.perform(delete("/cozinhas/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.error").value("Cozinha não encontrada"));
    }
}
