package com.vitoriadeveloper.vifood.infra.adapters.model.mapper;

import com.vitoriadeveloper.vifood.domain.model.State;
import com.vitoriadeveloper.vifood.infra.adapters.model.dto.response.StateResponse;

import java.util.List;

import static java.util.stream.Collectors.toList;

public class StateMapper {

    public static StateResponse toResponse(State request) {
        return new StateResponse(
                request.getId(),
                request.getNome(),
                request.getSigla()
        );
    }

    public static List<StateResponse> toResponseList(List<State> response) {
        return response.stream().map(StateMapper::toResponse).toList();
    }
}
