package com.allways.domain.category.repository;

import com.allways.domain.category.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CategoryRepository extends JpaRepository<Category,Long> {
    // Update 생긴다면 update 대한 내용이 추가되야 할 듯
}
