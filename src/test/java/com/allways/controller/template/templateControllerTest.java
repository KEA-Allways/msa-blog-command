package com.allways.controller.template;

import com.allways.domain.template.controller.TemplateCommandController;
import com.allways.domain.template.exception.TemplateNotFoundException;
import com.allways.domain.template.service.TemplateCommandService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class templateControllerTest {

    @InjectMocks
    TemplateCommandController templateController;
    @Mock
    TemplateCommandService templateCommandService;
    MockMvc mockMvc;

    @BeforeEach
    void beforeEach() {
        mockMvc = MockMvcBuilders.standaloneSetup(templateController).build();
    }

    // createTest

    // updateTest

    // deleteTest

}
