package com.kosa.springbootdeveloper.dto;

import com.kosa.springbootdeveloper.domain.Article;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ArticleAddRequestDto {

    private String title;
    private String content;

    public Article toEntity() {
        return Article.builder()
                .title(this.title)
                .content(this.content)
                .build();
    }
}
