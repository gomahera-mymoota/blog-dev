package com.kosa.springbootdeveloper.dto.token;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreateAccessTokenRequestDto {

    private String refreshToken;
}
