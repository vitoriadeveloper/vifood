package com.vitoriadeveloper.vifood.infra.adapters.model.dto.response;

import java.util.List;

public record PaginationResponse<T>(
    List<T> dados,
    int pagina,
    int tamanho,
    long total,
    int totalPaginas,
    boolean primeira,
    boolean ultima,
    boolean temProxima,
    boolean temAnterior
) {
}

