package com.allways.domain.reply.controller;

import com.allways.common.factory.reply.ReplyCreateRequestFactory;
import com.allways.common.factory.reply.ReplyUpdateRequestFactory;
import com.allways.domain.reply.dto.ReplyCreateRequest;
import com.allways.domain.reply.dto.ReplyUpdateRequest;
import com.allways.domain.reply.service.ReplyService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ReplyCommandControllerTest {
    @Mock private ReplyService replyService;
    @InjectMocks private ReplyCommandController replyCommandController;
    private MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(replyCommandController).build();
    }

    @Test
    void createReplyTest() throws Exception {
        // Given
        Long postSeq = 1L;
        ReplyCreateRequest createRequest = ReplyCreateRequestFactory.createReplyCreateRequest();

        // When, Then
        MvcResult result = mockMvc.perform(post("/api/post/{postSeq}/reply", postSeq)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("userSeq", String.valueOf(1L))
                        .content(asJsonString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn(); // Modify this based on your Response structure

        // header로 부터 userSeq를 읽어옴
        MockHttpServletRequest request = result.getRequest();
        String userSeq = request.getHeader("userSeq");

        // Verify
        // replyService가 createReply를 수행하는지 확인
        verify(replyService).createReply(createRequest, postSeq, Long.parseLong(userSeq));
    }

    @Test
    void updateReplyTest() throws Exception {
        // Given
        Long replySeq = 1L;
        ReplyUpdateRequest updateRequest = ReplyUpdateRequestFactory.createReplyUpdateRequest();

        // When, Then
        mockMvc.perform(put("/api/post/reply/{ReplySeq}", replySeq)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updateRequest)))
                .andExpect(status().isOk());

        // Verify
        // replyService가 updateReply를 수행하는지 확인
        verify(replyService).updateReply(replySeq, updateRequest);
    }

    @Test
    void deleteReply() throws Exception {
        // Given
        Long replySeq = 1L;

        mockMvc.perform(delete("/api/post/reply/{replySeq}", replySeq))
                .andExpect(status().isOk());

        // Verify
        // replyService가 deleteReply를 수행하는지 확인
        verify(replyService).deleteReply(replySeq);
    }

    // Utility method to convert object to JSON string
    private String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
