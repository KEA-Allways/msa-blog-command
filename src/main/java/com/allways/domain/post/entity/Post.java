package com.allways.domain.post.entity;

import javax.persistence.*;

import com.allways.common.EntityDate;
import com.allways.domain.category.entity.Category;
import com.allways.common.feign.user.UserDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_seq", nullable = false)
	// @OnDelete(action = OnDeleteAction.CASCADE)
	private Category category;

	//post 와 유저간의 관계
	// @ManyToOne(fetch = FetchType.LAZY)
	// @JoinColumn(name = "user_seq", nullable = false)
	// // @OnDelete(action = OnDeleteAction.CASCADE)
	// private User user;

	@Transient //데이터베이스에 저장하지 않도록 설정
	private UserDto user;

	//post 와 image 간의 관계
	@OneToMany(mappedBy = "post",cascade = CascadeType.PERSIST,orphanRemoval = true)
	private List<Image> images; //3

	public Post(String postTitle, String postContent, UserDto user
			, Category category, List<Image> images){
		this.postTitle=postTitle;
		this.postContent=postContent;
		this.user=user;
//		this.postView =postView;
		this.category=category;
		this.images=new ArrayList<>();
	}
}
