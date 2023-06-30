package com.kosa.springbootdeveloper.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAddRequestDto {
    private String email;
    private String password;
}
