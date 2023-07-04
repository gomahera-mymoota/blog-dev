package com.kosa.springbootdeveloper.dto.token;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CreateAccessTokenResponseDto {

    private String accessToken;
}
