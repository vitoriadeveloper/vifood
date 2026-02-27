package com.vitoriadeveloper.vifood.domain.ports.in;

import com.vitoriadeveloper.vifood.domain.exceptions.PaymentMethodNotFoundException;
import com.vitoriadeveloper.vifood.domain.model.PaymentMethod;

import java.util.List;
import java.util.UUID;

public interface IPaymentMethodUseCasePort {
    List<PaymentMethod> findAll();
    PaymentMethod findById(UUID id) throws PaymentMethodNotFoundException;
}
