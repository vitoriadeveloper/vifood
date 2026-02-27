package com.vitoriadeveloper.vifood.domain.ports.out;

import com.vitoriadeveloper.vifood.domain.model.PaymentMethod;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IPaymentMethodRepositoryPort {
    List<PaymentMethod> findAll();
    Optional<PaymentMethod> findById(UUID id);
}
