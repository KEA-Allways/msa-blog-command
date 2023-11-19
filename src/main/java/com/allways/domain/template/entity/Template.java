package com.allways.domain.template.entity;

import javax.persistence.*;

import com.allways.common.EntityDate;

import com.allways.domain.template.dto.TemplateUpdateRequest;
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
	private String templateTitle;

	@Column
	@Lob
	private String templateContent;

	@Column
	private Long userSeq;

	public Template(String templateTitle, String templateContent, Long userSeq) {
		this.templateTitle = templateTitle;
		this.templateContent = templateContent;
		this.userSeq = userSeq;
	}
}
