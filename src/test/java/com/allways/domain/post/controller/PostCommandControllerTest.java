package com.allways.domain.post.controller;

import com.allways.common.factory.post.PostCreateRequestFactory;
import com.allways.common.factory.post.PostUpdateRequestFactory;
import com.allways.domain.post.dto.PostUpdateRequest;
import com.allways.domain.post.service.PostService;
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
    @Mock private PostService postService;
    @InjectMocks private PostCommandController postCommandController;

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(postCommandController).build();
    }

    @Test
    void createPostTest() throws Exception {
        // Given
        PostCreateRequest createRequest = PostCreateRequestFactory.createPostCreateRequest();

        // When, Then
        MvcResult result = mockMvc.perform(post("/api/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("userSeq", String.valueOf(1L))
                        .content(asJsonString(createRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        MockHttpServletRequest request = result.getRequest();
        String userSeq = request.getHeader("userSeq");

        verify(postService).createPost(createRequest, Long.parseLong(userSeq));
    }

    @Test
    void updatePostTest() throws Exception {
        // Given
        Long postSeq = 1L;
        PostUpdateRequest updateRequest = PostUpdateRequestFactory.createPostUpdateRequest();

        // When, Then
        mockMvc.perform(put("/api/post/{postSeq}", postSeq)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(updateRequest)))
                .andExpect(status().isOk());

        verify(postService).updatePost(updateRequest, postSeq);
    }

    @Test
    void deletePostTest() throws Exception {
        // Given
        Long postSeq = 1L;

        // When
        doNothing().when(postService).deletePost(postSeq);

        // Then
        mockMvc.perform(delete("/api/post/{postSeq}", postSeq))
                .andExpect(status().isOk());

        verify(postService).deletePost(postSeq);
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
