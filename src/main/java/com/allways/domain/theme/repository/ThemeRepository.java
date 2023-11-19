package com.allways.domain.theme.repository;

import com.allways.domain.theme.dto.Theme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThemeRepository extends JpaRepository<Theme, Long> {

}
