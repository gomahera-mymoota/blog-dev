package com.kosa.springbootdeveloper.service;

import com.kosa.springbootdeveloper.domain.Article;
import com.kosa.springbootdeveloper.dto.ArticleAddRequestDto;
import com.kosa.springbootdeveloper.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Article save(ArticleAddRequestDto dto) {
        return articleRepository.save(dto.toEntity());
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }
}
