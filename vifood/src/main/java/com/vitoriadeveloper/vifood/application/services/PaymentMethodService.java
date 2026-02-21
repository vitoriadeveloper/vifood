package com.vitoriadeveloper.vifood.application.services;

import com.vitoriadeveloper.vifood.domain.model.PaymentMethod;
import com.vitoriadeveloper.vifood.domain.ports.in.IPaymentMethodUseCasePort;
import com.vitoriadeveloper.vifood.domain.ports.out.IPaymentMethodRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Service
@AllArgsConstructor
public class PaymentMethodService implements IPaymentMethodUseCasePort {

    private final IPaymentMethodRepositoryPort repository;


    @Override
    public List<PaymentMethod> findAll() {
        return repository.findAll();
    }
}
