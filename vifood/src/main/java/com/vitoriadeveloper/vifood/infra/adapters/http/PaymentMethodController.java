package com.vitoriadeveloper.vifood.infra.adapters.http;

import com.vitoriadeveloper.vifood.application.services.PaymentMethodService;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.PaymentMethodResponse;
import com.vitoriadeveloper.vifood.infra.adapters.model.mapper.PaymentMethodMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/formas-pagamento")
@RequiredArgsConstructor
public class PaymentMethodController {

    private final PaymentMethodService service;

    @GetMapping
    public List<PaymentMethodResponse> findAll() {
        var paymentMethods = service.findAll();
        return PaymentMethodMapper.toResponseList(paymentMethods);
    }
}
