package com.kosa.springbootdeveloper.dto.article;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ArticleUpdateRequestDto {

    private String title;
    private String content;
}
