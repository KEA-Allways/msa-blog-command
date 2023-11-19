package com.allways.domain.category.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.allways.common.EntityDate;
import com.allways.domain.theme.entity.Theme;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

	//나중에 생각해보기
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "theme_seq")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Theme theme;
	//
	public Category(String name,Category Theme){

	}


}
