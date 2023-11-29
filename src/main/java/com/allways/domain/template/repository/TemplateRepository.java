package com.allways.domain.template.repository;

import com.allways.domain.template.entity.Template;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface TemplateRepository extends JpaRepository<Template, Long> {
    @Modifying
    @Query("UPDATE Template t SET t.templateContent = :templateContent, t.templateTitle = :templateTitle WHERE t.templateSeq = :templateSeq")
    void updateBySeq(Long templateSeq, String templateContent, String templateTitle);
}
