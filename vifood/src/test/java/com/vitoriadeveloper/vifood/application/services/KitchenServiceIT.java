package com.vitoriadeveloper.vifood.application.services;

import com.vitoriadeveloper.vifood.domain.exceptions.KitchenNotFoundException;
import com.vitoriadeveloper.vifood.domain.model.Kitchen;
import jakarta.transaction.Transactional;
import jakarta.validation.ConstraintViolationException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@ActiveProfiles("test")
@Transactional
public class KitchenServiceIT {

    private final KitchenService service;

    KitchenServiceIT(KitchenService service) {
        this.service = service;
    }

    @Test
    void shouldCreateKitchen() {
        // context
        Kitchen kitchen = new Kitchen();
        kitchen.setNome("Brasileira");

        // action
        Kitchen createdKitchen = service.create(kitchen);

        // assert
        assertNotNull(createdKitchen.getId());
        assertEquals(kitchen.getNome(), createdKitchen.getNome());
    }

    @Test
    void shouldFindKitchenById() {
        // context
        Kitchen kitchen = new Kitchen();
        kitchen.setNome("Italiana");

        Kitchen createdKitchen = service.create(kitchen);

        // action
        Kitchen foundKitchen = service.findById(createdKitchen.getId());

        // assert
        assertNotNull(foundKitchen);
        assertEquals(createdKitchen.getId(), foundKitchen.getId());
    }

    @Test
    void shouldFindAllKitchens() {
        // context
        Kitchen kitchen1 = new Kitchen();
        kitchen1.setNome("Mexicana");
        service.create(kitchen1);

        Kitchen kitchen2 = new Kitchen();
        kitchen2.setNome("Japonesa");
        service.create(kitchen2);

        // action
        var kitchens = service.findAll();

        // assert
        assertNotNull(kitchens);
        assertEquals(2, kitchens.size());
    }

    @Test
    void shouldUpdateKitchen() {
        // context
        Kitchen kitchen = new Kitchen();
        kitchen.setNome("Francesa");

        Kitchen createdKitchen = service.create(kitchen);

        // action
        createdKitchen.setNome("Coreana");
        Kitchen updatedKitchen = service.updateById(createdKitchen.getId(), createdKitchen);

        // assert
        assertNotNull(updatedKitchen);
        assertEquals("Coreana", updatedKitchen.getNome());
    }

    @Test
    void shouldDeleteKitchen() {
        // context
        Kitchen kitchen = new Kitchen();
        kitchen.setNome("Indiana");

        Kitchen createdKitchen = service.create(kitchen);

        // action
        service.deleteById(createdKitchen.getId());

        // assert
        KitchenNotFoundException exception =
                assertThrows(KitchenNotFoundException.class, () ->
                        service.findById(createdKitchen.getId())
                );

        assertEquals(
                "Cozinha de id: " + createdKitchen.getId() + " não encontrada!",
                exception.getMessage()
        );
    }

    @Test
    void shouldNotBeAbleCreateKitchenWithoutName() {
        // context
        Kitchen kitchen = new Kitchen();

        assertThrows(ConstraintViolationException.class, () -> service.create(kitchen));
    }
}
