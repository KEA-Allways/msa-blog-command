package com.allways.domain.theme.repository;

import com.allways.common.factory.theme.ThemeFactory;
import com.allways.domain.theme.entity.Theme;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // 실제 데이터베이스 사용
@SpringBootTest
@ActiveProfiles("test")
public class ThemeRepositoryTest {
    @Autowired private ThemeRepository themeRepository;
    @Autowired private EntityManager entityManager;

    @Test
    @Transactional
    void updateByThemeSeqTest() {
        // Given
        String newThemeName = "updateThemeName";
        Long newThemeOrder = 10L;

        Theme theme = ThemeFactory.createTheme();
        entityManager.persist(theme);

        // When
        themeRepository.updateByThemeSeq(
                theme.getThemeSeq(),
                newThemeName,
                newThemeOrder);
        entityManager.flush();
        entityManager.clear();
        Theme updatedTheme = themeRepository.findById(theme.getThemeSeq()).orElse(null);

        // Then
        assertThat(updatedTheme).isNotNull();
        assertThat(updatedTheme.getThemeName()).isEqualTo(newThemeName);
        assertThat(updatedTheme.getThemeOrder()).isEqualTo(newThemeOrder);

        themeRepository.deleteById(updatedTheme.getThemeSeq());
    }
}
