package com.allways.domain.reply.service;

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

    private final ReplyRepository replyRepository;

    @Transactional
    public void createReply(ReplyCreateRequest req, Long postSeq, Long userSeq) {
        //댓글 저장
        replyRepository.save(new Reply(req.getReplyContent(), postSeq, userSeq));
    }

    @Transactional
    public void deleteReply(Long replySeq) {
        replyRepository.deleteById(replySeq);
    }
}
