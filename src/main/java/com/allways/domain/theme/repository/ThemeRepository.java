package com.allways.domain.theme.repository;

import com.allways.domain.theme.dto.ThemeUpdateRequest;
import com.allways.domain.theme.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ThemeRepository extends JpaRepository<Theme, Long> {
    @Modifying
    @Query("UPDATE Theme t SET t.themeName = :themeName, t.themeOrder = :themeOrder  WHERE t.themeSeq= :themeSeq")
    void updateByThemeSeq(Long themeSeq, String themeName, Long themeOrder);
}
