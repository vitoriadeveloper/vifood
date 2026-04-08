package com.vitoriadeveloper.vifood.application.services;

import com.vitoriadeveloper.vifood.domain.model.DailySales;
import com.vitoriadeveloper.vifood.domain.ports.out.IStatisticsRepositoryPort;
import lombok.AllArgsConstructor;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.util.List;

@Service
@AllArgsConstructor
public class PdfService {
    private final IStatisticsRepositoryPort repository;

    @Autowired
    private TemplateEngine templateEngine;

    public byte[] generatePdf(List<DailySales> sales) {
        Context context = new Context();
        context.setVariable("dailySales", sales);

        String html = templateEngine.process("statistics", context);
        System.out.println(html);
        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(outputStream);
            return outputStream.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao generar PDF", e);
        }
    }
}
