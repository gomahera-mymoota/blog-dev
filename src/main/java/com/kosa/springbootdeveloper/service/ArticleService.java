package com.kosa.springbootdeveloper.service;

import com.kosa.springbootdeveloper.domain.Article;
import com.kosa.springbootdeveloper.dto.article.ArticleAddRequestDto;
import com.kosa.springbootdeveloper.dto.article.ArticleUpdateRequestDto;
import com.kosa.springbootdeveloper.repository.ArticleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Article save(ArticleAddRequestDto dto, String userName) {
        return articleRepository.save(dto.toEntity(userName));
    }

    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    public Article findById(Long id) {
        return articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
    }

    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }

    @Transactional
    public Article updateById(Long id, ArticleUpdateRequestDto dto) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));

        article.update(dto.getTitle(), dto.getContent());

        return article;
    }

    public void delete(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
        authorizeArticleAuthor(article);
        articleRepository.delete(article);
    }

    @Transactional
    public Article update(Long id, ArticleUpdateRequestDto dto) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found: " + id));
        authorizeArticleAuthor(article);
        article.update(dto.getTitle(), dto.getContent());

        return article;
    }

    private static void authorizeArticleAuthor(Article article) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!article.getAuthor().equals(userName)) {
            throw new IllegalArgumentException("not authorized");
        }
    }
}
