package com.allways.domain.post.controller;

import com.allways.common.factory.post.PostCreateRequestFactory;
import com.allways.common.factory.post.PostUpdateRequestFactory;
import com.allways.domain.post.dto.PostUpdateRequest;
import com.allways.domain.post.service.PostCommandService;
import com.allways.domain.post.dto.PostCreateRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.http.MediaType;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class PostCommandControllerTest {
    private MockMvc mockMvc;
    @Mock private PostCommandService postCommandService;
    @InjectMocks private PostCommandController postCommandController;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(postCommandController).build();
    }

    @Test
    void createPostTest() throws Exception {
        PostCreateRequest createRequest = PostCreateRequestFactory.createPostCreateRequest();

        MvcResult result = mockMvc.perform(post("/api/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("userSeq", String.valueOf(1L))
                        .content(asJsonString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        MockHttpServletRequest request = result.getRequest();
        String userSeq = request.getHeader("userSeq");

        verify(postCommandService).createPost(createRequest, Long.parseLong(userSeq));
    }

    @Test
    void updatePostTest() throws Exception {
        PostUpdateRequest updateRequest = PostUpdateRequestFactory.createPostUpdateRequest();

        Long postSeq = 1L;

        mockMvc.perform(put("/api/post/{postSeq}", postSeq)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updateRequest)))
                .andExpect(status().isOk());

        verify(postCommandService).updatePost(updateRequest, postSeq);
    }

    @Test
    void deletePostTest() throws Exception {
        Long postSeq = 1L;

        doNothing().when(postCommandService).deletePost(postSeq);

        mockMvc.perform(delete("/api/post/{postSeq}", postSeq))
                .andExpect(status().isOk());

        verify(postCommandService).deletePost(postSeq);
    }

    // Helper method to convert objects to JSON string
    private String asJsonString(Object object) {
        try {
            return new ObjectMapper().writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
