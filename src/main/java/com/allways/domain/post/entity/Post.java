package com.allways.domain.post.entity;

import javax.persistence.*;

import com.allways.common.EntityDate;
import com.allways.domain.category.entity.Category;
import com.allways.common.feign.user.UserDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

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

//	public ImageUpdatedResult update(PostUpdateRequest req){
//		this.postTitle=req.getTitle();
//		this.postContent=req.getContent();
//		this.userSeq = req.getUserSeq();
//		ImageUpdatedResult result =findImageUpdatedResult(req.getAddedImages(),req.getDeletedImages());
//		addImages(result.getAddedImages());
//		deleteImages(result.getDeletedImages());
//		return result;
//	}

	@Getter
	@AllArgsConstructor
	public static class ImageUpdatedResult{
		private List<MultipartFile> addedImageFiles;
		private List<Image> addedImages;
		private List<Image> deletedImages;

	}


	private void addImages(List<Image> added){ //5
		added.stream().forEach(i->{
			images.add(i);
			i.initPost(this);
		});
	}


	private void deleteImages(List<Image> deleted){
		deleted.stream().forEach(di->this.images.remove(di));
	}



	private ImageUpdatedResult findImageUpdatedResult(List<MultipartFile> addedImageFiles,List<Long> deletedImageIds){
		List<Image> addedImages = convertImageFilesToImages(addedImageFiles);
		List<Image> deletedImages = convertImageIdsToImages(deletedImageIds);
		return new ImageUpdatedResult(addedImageFiles,addedImages,deletedImages);
	}

	private List<Image> convertImageIdsToImages(List<Long> imageIds) {
		return imageIds.stream()
				.map(id->convertImageIdImage(id))
				.filter(i->i.isPresent())
				.map(i->i.get())
				.collect(toList());
	}
	private Optional<Image> convertImageIdImage(Long id){
		return this.images.stream().filter(i->i.getId().equals(id)).findAny();
	}
	private List<Image> convertImageFilesToImages(List<MultipartFile> imageFiles) {
		return imageFiles.stream().map(imageFile->new Image(imageFile.getOriginalFilename())).collect(toList());
	}
}
