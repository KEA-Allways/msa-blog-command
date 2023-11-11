package com.allways.domain.category.repository;

import com.allways.domain.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    //계층화 작업은 나중에 생각하기
//    @Query("select c from Category c left join c.parent p order by p.id asc nulls first,c.id asc")
//    List<Category> findAllOrderByParentIdAscNullsFirstCategoryIdAsc()  ;
}
