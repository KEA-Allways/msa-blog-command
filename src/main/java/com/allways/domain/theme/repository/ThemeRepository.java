package com.allways.domain.theme.repository;

import com.allways.domain.theme.entity.Theme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ThemeRepository extends JpaRepository<Theme, Long> {

    // t. 안적혀 있어서 오류 뜨고 있었음
    @Query("select COALESCE(max(t.themeOrder), 0) from Theme t where t.userSeq = :userSeq")
    Long findLastThemeOrderByUserSeq(Long userSeq);

    // themeUpdate 내용 추가 필요
//    @Modifying
//    @Query(update Theme t set  where)
//    void updateThemeByThemeSeq();
}
