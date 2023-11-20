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
//@Entity(name = "CategoryEntity")
@Entity
public class Post extends EntityDate  {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long postSeq;

	@Column(nullable = false)
	private String postTitle;

	@Column(nullable = false)
	@Lob
	private String postContent;

//	//조회수
//	@Column
//	private Long postView;

	 @Column
	 private Long categorySeq;

	//post 와 유저간의 관계
	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "user_seq", nullable = false)
	// // @OnDelete(action = OnDeleteAction.CASCADE)
	// private User user;

	@Column
	private Long userSeq;

	//post 와 image 간의 관계
//	@OneToMany(mappedBy = "post",orphanRemoval = true)
//	private List<Image> images; //3

	public Post(String postTitle, String postContent, Long userSeq, Long categorySeq) {
		this.postTitle = postTitle;
		this.postContent = postContent;
		this.userSeq = userSeq;
//		this.postView =postView;
		this.categorySeq = categorySeq;
	}
}
