package com.allways.domain.reply.entity;

import javax.persistence.*;

import com.allways.common.EntityDate;

import lombok.*;

@Getter
@Setter
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


	public Reply(String content, Long postSeq, Long userSeq) {
		this.replyContent = content;
		this.postSeq = postSeq;
		this.userSeq = userSeq;
	}
}
