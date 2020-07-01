package com.telusko.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telusko.demo.model.Article;
import com.telusko.demo.repository.ArticleRepository;

@Service
public class ArticleServiceImplementation implements ArticleService {

	public static String uploadDirectory = System.getProperty("user.dir")+"/uploads";
	
    @Autowired
    private ArticleRepository articleRepository;
	
	@Override
	public List<Article> findAll() {
		return articleRepository.findAll();
	}

	@Override
	public void save(Article article) {
		articleRepository.save(article);

	}

	@Override
	public Optional<Article> findById(int article_id) {
		return articleRepository.findById(article_id);
	}

	@Override
	public void delete(int article_id) {
		articleRepository.deleteById(article_id);
	}

	public Article getOne(int article_id) {
		return articleRepository.getOne(article_id);
	}
	
	/*
	@Override
	public void decrementStock(Article article) {
		// TODO Auto-generated method stub

	}

	@Override
	public Article getOne(int article_id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Object[]> getArticles() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	*/

}
