package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.repository.ArticleRepository;

@Controller
@RequestMapping("/articles")
public class ArticleController {

	private ArticleRepository repository;

	@Autowired
	public ArticleController(ArticleRepository repository) {
		this.repository = repository;
	}

	@GetMapping
	public String list(Model model) {
		model.addAttribute("articles", repository.findAll());
		return "articles/list";
	}

	@GetMapping("{id}")
	public String detail(@PathVariable Integer id, Model model) {
		model.addAttribute("articles", repository.findOne(id));
		return "articles/detail";
	}

}
