package com.allways.domain.theme.repository;

import com.allways.domain.theme.domain.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ThemeRepository extends JpaRepository<Theme, Long> {

    @Query("select COALESCE(max(themeOrder), 0) from Theme t where t.userSeq = :userSeq")
    Long findLastThemeOrderByUserSeq(Long userSeq);
}
