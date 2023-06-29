package com.kosa.springbootdeveloper.controller;

import com.kosa.springbootdeveloper.domain.Article;
import com.kosa.springbootdeveloper.dto.ArticleListViewResponseDto;
import com.kosa.springbootdeveloper.dto.ArticleViewResponseDto;
import com.kosa.springbootdeveloper.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final ArticleService articleService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponseDto> articles = articleService.findAll()
                .stream()
                .map(ArticleListViewResponseDto::new)
                .toList();
        model.addAttribute("articles", articles);

        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticleById(Model model, @PathVariable Long id) {
        Article article = articleService.findById(id);
        model.addAttribute("article", new ArticleViewResponseDto(article));

        return "article";
    }

    @GetMapping("/new-article")
    public String createArticle(Model model, @RequestParam(required = false) Long id) {
        if (id == null) {
            model.addAttribute("article", new ArticleViewResponseDto());
        } else {
            Article article = articleService.findById(id);
            model.addAttribute("article", new ArticleViewResponseDto(article));
        }

        return "newArticle";
    }
}
