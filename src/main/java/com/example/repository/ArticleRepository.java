package com.example.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.entity.Article;

@Repository
public class ArticleRepository {

	private List<Article> articles = Arrays.asList(new Article(1, "Joe User の記事", 3),
			new Article(2, "Jane User の記事", 2));

	public List<Article> findAll() {
		return articles;
	}

	public Article findOne(Integer id) {
		return articles.stream().filter(e -> e.getId().equals(id)).findFirst().orElse(new Article());
	}
}
