package com.vitoriadeveloper.vifood.infra.utils;

import com.vitoriadeveloper.vifood.domain.exceptions.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
@AllArgsConstructor
public class ImageValidator {
    public void validate(MultipartFile file) {

        if (file.isEmpty()) {
            throw new BusinessException("Arquivo  não pode estar vazio");
        }

        String contentType = file.getContentType();

        if (contentType == null || (!contentType.contains("image/jpeg") && !contentType.contains("image/png")))
            throw new BusinessException("Tipo de arquivo inválido");
    }
}
