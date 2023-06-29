package com.kosa.springbootdeveloper.controller;

import com.kosa.springbootdeveloper.dto.ArticleListViewResponseDto;
import com.kosa.springbootdeveloper.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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
}
