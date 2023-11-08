package com.allways.domain.template.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import com.allways.common.EntityDate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Template extends EntityDate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long templateSeq;

	@Column
	private String templateName;

	@Column
	@Lob
	private String templateContent;

	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "theme_seq")
	// private User user;

}