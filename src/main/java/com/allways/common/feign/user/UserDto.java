package com.allways.common.feign.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long seq;
    private String id;
    private String password;
    private String nickname;
    private String email;
    private String profileImgSeq;
}
