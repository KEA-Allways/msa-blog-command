package com.allways.domain.template.dto;

import com.allways.common.feign.user.User;
import com.allways.common.feign.user.UserDto;
import com.allways.common.feign.user.UserFeignClient;
import com.allways.domain.template.entity.Template;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemplateCreateRequest {

    private static UserFeignClient userFeignClient;

    @NotBlank(message = "템플릿 제목을 입력해주세요.")
    private String templateTitle;

    @NotBlank(message = "템플릿 내용을 입력해주세요.")
    private String templateContent;

    public static Template toEntity(TemplateCreateRequest req) {
        UserDto userDto = userFeignClient.getUserById(1L);
        User user = new User(userDto.getId(), userDto.getPassword(),
                userDto.getNickname(), userDto.getEmail(), userDto.getProfileImgSeq());
        return new Template(req.templateTitle, req.templateContent, user);
    }
}
