package com.allways.domain.template.repository;

import com.allways.common.factory.template.TemplateFactory;
import com.allways.domain.template.entity.Template;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실제 데이터베이스 사용
@SpringBootTest
@ActiveProfiles("test")
public class TemplateRepositoryTest {
    @Autowired private TemplateRepository templateRepository;
    @Autowired private EntityManager entityManager;

    @Test
    @Transactional
    void updateByTemplateSeqTest() {
        // Given
        String newTemplateTitle = "updateTemplateTitle";
        String newTemplateContent = "updateTemplateContent";

        Template template = TemplateFactory.createTemplate();
        entityManager.persist(template);

        // When
        templateRepository.updateBySeq(
                template.getTemplateSeq(),
                newTemplateTitle,
                newTemplateContent);
        entityManager.flush();
        entityManager.clear();

        Template updatedTemplate = templateRepository.findById(template.getTemplateSeq()).orElse(null);

        // Then
        assertNotNull(updatedTemplate);
        assertEquals(newTemplateTitle, updatedTemplate.getTemplateContent());
        assertEquals(newTemplateContent, updatedTemplate.getTemplateTitle());
    }
}
