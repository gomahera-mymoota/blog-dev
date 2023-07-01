package com.kosa.springbootdeveloper.controller;

import com.kosa.springbootdeveloper.domain.Article;
import com.kosa.springbootdeveloper.dto.article.ArticleAddRequestDto;
import com.kosa.springbootdeveloper.dto.article.ArticleResponseDto;
import com.kosa.springbootdeveloper.dto.article.ArticleUpdateRequestDto;
import com.kosa.springbootdeveloper.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ArticleController {

    private final ArticleService articleService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody ArticleAddRequestDto dto) {
        Article savedArticle = articleService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<ArticleResponseDto>> findAllArticles() {
        List<ArticleResponseDto> articles = articleService.findAll()
                .stream()
                .map(ArticleResponseDto::new)
                .toList();
        return ResponseEntity.ok()
                .body(articles);
    }

    @GetMapping("/api/articles/{id}")
    public ResponseEntity<ArticleResponseDto> findArticleById(@PathVariable Long id) {
        Article article = articleService.findById(id);
        return ResponseEntity.ok()
                .body(new ArticleResponseDto(article));
    }

    @DeleteMapping("/api/articles/{id}")
    public ResponseEntity<Void> deleteArticleById(@PathVariable Long id) {
        articleService.deleteById(id);
        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/articles/{id}")
    public ResponseEntity<Article> updateArticleById(
            @PathVariable Long id,
            @RequestBody ArticleUpdateRequestDto dto) {
        Article updatedArticle = articleService.updateById(id, dto);

        return ResponseEntity.ok()
                .body(updatedArticle);
    }
}
