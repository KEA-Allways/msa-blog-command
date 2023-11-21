package com.allways.domain.reply.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.allways.common.EntityDate;
import com.allways.domain.post.entity.Post;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Reply extends EntityDate  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long replySeq;

	@Column
	private String replyContent;

	@Column
	private Long postSeq;

	@Column
	private Long userSeq;


	public Reply(String content, Long postSeq, Long UserSeq) {
		this.replyContent = content;
		this.postSeq = postSeq;
		this.userSeq = UserSeq;
	}
}
