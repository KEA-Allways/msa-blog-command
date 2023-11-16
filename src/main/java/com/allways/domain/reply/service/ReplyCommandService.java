package com.allways.domain.reply.service;

import com.allways.domain.post.entity.Post;
import com.allways.domain.post.exception.PostNotFoundException;
import com.allways.domain.post.repository.PostRepository;
import com.allways.domain.reply.repository.ReplyRepository;
import com.allways.domain.reply.entity.Reply;
import com.allways.domain.reply.dto.ReplyCreateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReplyCommandService {

    private final PostRepository postRepository;
    private final ReplyRepository replyRepository;

    @Transactional
    public void createReply(ReplyCreateRequest req) {

        //댓글이 저장될 게시글 정보 조회
        Post post = postRepository.findById(req.getPostSeq()).orElseThrow(PostNotFoundException::new);

        //테스트
        System.out.println(post.getPostSeq());

        //댓글 저장
        Reply reply = replyRepository.save(new Reply(req.getReplyContent(), post, req.getUserSeq()));

        //테스트
        System.out.println(reply.getReplySeq());
        System.out.println(reply.getReplyContent());

    }

    @Transactional
    public void deleteReply(Long replySeq) {
        Reply reply = replyRepository.findById(replySeq).orElse(null);
        replyRepository.delete(reply);
    }
}
