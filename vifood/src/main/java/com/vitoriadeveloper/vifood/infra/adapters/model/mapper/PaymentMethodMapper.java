package com.vitoriadeveloper.vifood.infra.adapters.model.mapper;

import com.vitoriadeveloper.vifood.domain.model.PaymentMethod;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.PaymentMethodResponse;

import java.util.List;

public class PaymentMethodMapper {

    public static PaymentMethodResponse toResponse(PaymentMethod paymentMethod) {
        return new PaymentMethodResponse(
                paymentMethod.getId(),
                paymentMethod.getDescricao()
        );
    }

    public static List<PaymentMethodResponse> toResponseList(List<PaymentMethod> paymentMethods) {
        return paymentMethods.stream()
                .map(PaymentMethodMapper::toResponse)
                .toList();
    }
}
