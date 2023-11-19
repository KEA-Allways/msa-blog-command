package com.allways.domain.category.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.allways.common.EntityDate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category extends EntityDate  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long categorySeq;

	@Column
	private String categoryName;

	@Column
	private Long categoryOrder;

	@Column
    private Long themeSeq;

	//나중에 생각해보기
//	@ManyToOne(fetch = FetchType.LAZY)
//	@JoinColumn(name = "themeSeq")
//	@OnDelete(action = OnDeleteAction.CASCADE)
//	private Theme theme;

	public Category(String categoryName, Long categoryOrder ,Long themeSeq){
        this.categoryName = categoryName;
        this.categoryOrder = categoryOrder;
        this.themeSeq =themeSeq;
	}

}
