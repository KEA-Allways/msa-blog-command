package com.allways.domain.post.entity;

import javax.persistence.*;

import com.allways.common.EntityDate;
import com.allways.domain.category.entity.Category;
import com.allways.common.feign.user.UserDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

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

	@Column
	private Long postView;

//	//조회수
//	@Column
//	private Long postView;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "category_seq", nullable = false)
	// @OnDelete(action = OnDeleteAction.CASCADE)
	private Category category;


//	 @ManyToOne(fetch = FetchType.LAZY)
	 @Column(name = "user_seq", nullable = false)
	 // @OnDelete(action = OnDeleteAction.CASCADE)
	 private Long userSeq;



	//post 와 image 간의 관계
	@OneToMany(mappedBy = "post",cascade = CascadeType.PERSIST,orphanRemoval = true)
	private List<Image> images; //3

	public Post(String postTitle, String postContent, Long userSeq
			, Category category, List<Image> images){
		this.postTitle=postTitle;
		this.postContent=postContent;
		this.userSeq=userSeq;

		this.category=category;
		this.images=new ArrayList<>();
	}
}
