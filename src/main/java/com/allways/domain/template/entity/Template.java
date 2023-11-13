package com.allways.domain.template.entity;

import javax.persistence.*;

import com.allways.common.EntityDate;

import com.allways.common.feign.user.User;
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

	@ManyToOne // 다대일 관계 설정
	@JoinColumn(name = "user_seq") // User 엔터티의 기본 키와 연결될 외래 키
	private User user;

	public Template(String templateTitle, String templateContent, User user) {
		this.templateTitle = templateTitle;
		this.templateContent = templateContent;
		this.user = user;
	}

	public void update(TemplateUpdateRequest req) {
		this.templateTitle = req.getTemplateTitle();
		this.templateContent = req.getTemplateContent();
	}
}
