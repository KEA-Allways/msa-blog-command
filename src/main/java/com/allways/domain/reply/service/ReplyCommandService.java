package com.allways.domain.reply.service;

import com.allways.domain.post.domain.Post;
import com.allways.domain.post.repository.PostRepository;
import com.allways.domain.reply.ReplyRepository;
import com.allways.domain.reply.domain.Reply;
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
    public void create(ReplyCreateRequest req) {
        System.out.println("!!!!!!!!2");
        System.out.println(req.getReplyContent());
        System.out.println(req.getPostSeq());
        System.out.println(req.getUserSeq());

        Post post = postRepository.findById(req.getPostSeq()).orElse(null);

        if (post == null){
            System.out.println("!!!!!!!!");
        }

        System.out.println(post.getPostSeq());

        Reply reply = replyRepository.save(new Reply(req.getReplyContent(), post, req.getUserSeq()));
        System.out.println(reply.getReplySeq());
        System.out.println(reply.getReplyContent());

    }
}
