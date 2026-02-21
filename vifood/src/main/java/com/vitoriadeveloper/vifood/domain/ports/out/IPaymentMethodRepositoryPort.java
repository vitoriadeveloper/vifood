package com.vitoriadeveloper.vifood.domain.ports.out;

import com.vitoriadeveloper.vifood.domain.model.PaymentMethod;

import java.util.List;

public interface IPaymentMethodRepositoryPort {
    List<PaymentMethod> findAll();
}
