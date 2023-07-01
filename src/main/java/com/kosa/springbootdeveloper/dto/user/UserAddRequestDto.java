package com.kosa.springbootdeveloper.dto.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAddRequestDto {
    private String email;
    private String password;
}
