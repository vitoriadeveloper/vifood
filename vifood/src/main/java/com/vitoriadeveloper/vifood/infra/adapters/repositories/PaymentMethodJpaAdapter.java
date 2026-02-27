package com.vitoriadeveloper.vifood.infra.adapters.repositories;

import com.vitoriadeveloper.vifood.domain.model.PaymentMethod;
import com.vitoriadeveloper.vifood.domain.ports.out.IPaymentMethodRepositoryPort;
import com.vitoriadeveloper.vifood.infra.repositories.PaymentMethodRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class PaymentMethodJpaAdapter implements IPaymentMethodRepositoryPort {
    private final PaymentMethodRepository jpaRepository;
    @Override
    public List<PaymentMethod> findAll() {
        return jpaRepository.findAll();
    }

    @Override
    public Optional<PaymentMethod> findById(UUID id) {
        return jpaRepository.findById(id);
    }
}
