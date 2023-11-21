package com.allways.domain.post.entity;

import javax.persistence.*;

import com.allways.common.EntityDate;
import com.allways.domain.category.entity.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post extends EntityDate {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postSeq;

	@Column(nullable = false)
	private String postTitle;

	@Column(nullable = false)
	@Lob
	private String postContent;

	@Column
	private Long postView;

	@Column
	private Long categorySeq;

	@Column
	private Long userSeq;

	//post 와 image 간의 관계
	//	@OneToMany(mappedBy = "post",orphanRemoval = true)
	//	private List<Image> images; //3

	public Post(String postTitle, String postContent, Long userSeq,Long categorySeq) {
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.userSeq = userSeq;
		this.postView =0L;
		this.categorySeq = categorySeq;
	}
}
