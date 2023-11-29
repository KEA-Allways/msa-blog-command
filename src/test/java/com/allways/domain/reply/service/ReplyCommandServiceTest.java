package com.allways.domain.reply.service;

import com.allways.common.factory.reply.ReplyCreateRequestFactory;
import com.allways.common.factory.reply.ReplyUpdateRequestFactory;
import com.allways.domain.reply.dto.ReplyCreateRequest;
import com.allways.domain.reply.dto.ReplyUpdateRequest;
import com.allways.domain.reply.entity.Reply;
import com.allways.domain.reply.repository.ReplyRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ReplyCommandServiceTest {
    @Mock private ReplyRepository replyRepository;
    @InjectMocks private ReplyCommandService replyCommandService;
    @Captor private ArgumentCaptor<Reply> ReplyArgumentCaptor;

    @Test
    void createReplyTest() {
        // Given
        ReplyCreateRequest createRequest = ReplyCreateRequestFactory.createReplyCreateRequest();
        Long postSeq = 1L;
        Long userSeq = 1L;

        // When
        replyCommandService.createReply(createRequest, postSeq, userSeq);

        // Then
        verify(replyRepository).save(ReplyArgumentCaptor.capture());

        Reply savedReply = ReplyArgumentCaptor.getValue();

        assertEquals(createRequest.getReplyContent(), savedReply.getReplyContent());
    }

    @Test
    void updateReplyTest() {
        // Given
        Long replySeq = 1000L;
        ReplyUpdateRequest updateRequest = ReplyUpdateRequestFactory.createReplyUpdateRequest();

        // When
        replyCommandService.updateReply(replySeq, updateRequest);

        // Then
        verify(replyRepository).updateRepliesByReplySeq(
                replySeq, updateRequest.getReplyContent());
    }

    @Test
    void deleteReplyTest() {
        // Given
        Long replySeq = 1L;

        // When
        replyCommandService.deleteReply(replySeq);

        // Then
        verify(replyRepository).deleteById(replySeq);
    }
}
