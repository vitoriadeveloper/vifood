package com.vitoriadeveloper.vifood.domain.ports.in;

import com.vitoriadeveloper.vifood.domain.model.PaymentMethod;

import java.util.List;

public interface IPaymentMethodUseCasePort {
    List<PaymentMethod> findAll();
}
