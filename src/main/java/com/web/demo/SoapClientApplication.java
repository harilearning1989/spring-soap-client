package com.web.demo;

import com.web.demo.client.ArticleClient;
import com.web.demo.wsdl.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SoapClientApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SoapClientApplication.class, args);
	}

	@Autowired
	ArticleClient articleClient;
	@Override
	public void run(String... args) throws Exception {
		System.out.println("--- Get Article by Id ---");
		GetArticleByIdResponse articleByIdResponse = articleClient.getArticleById(1);
		ArticleInfo articleInfo = articleByIdResponse.getArticleInfo();
		System.out.println(articleInfo.getArticleId() + ", "+ articleInfo.getTitle()
				+ ", " + articleInfo.getCategory());

		System.out.println("--- Get all Articles ---");
		GetAllArticlesResponse allArticlesResponse = articleClient.getAllArticles();
		allArticlesResponse.getArticleInfo().stream()
				.forEach(e -> System.out.println(e.getArticleId() + ", "+ e.getTitle() + ", " + e.getCategory()));

		System.out.println("--- Add Article ---");
		String title = "Spring REST Security using Hibernate";
		String category = "Spring";
		AddArticleResponse addArticleResponse = articleClient.addArticle(title, category);
		articleInfo = addArticleResponse.getArticleInfo();
		if (articleInfo != null) {
			System.out.println(articleInfo.getArticleId() + ", "+ articleInfo.getTitle()
					+ ", " + articleInfo.getCategory());
		}
		ServiceStatus serviceStatus = addArticleResponse.getServiceStatus();
		System.out.println("StatusCode: " + serviceStatus.getStatusCode() +
				", Message: " + serviceStatus.getMessage());

		System.out.println("--- Update Article ---");
		articleInfo = new ArticleInfo();
		articleInfo.setArticleId(1);
		articleInfo.setTitle("Update:Java Concurrency");
		articleInfo.setCategory("Java");
		UpdateArticleResponse updateArticleResponse = articleClient.updateArticle(articleInfo);
		serviceStatus = updateArticleResponse.getServiceStatus();
		System.out.println("StatusCode: " + serviceStatus.getStatusCode() +
				", Message: " + serviceStatus.getMessage());
		System.out.println("--- Delete Article ---");
		long articleId = 3;
		DeleteArticleResponse deleteArticleResponse = articleClient.deleteArticle(articleId);
		serviceStatus = deleteArticleResponse.getServiceStatus();
		System.out.println("StatusCode: " + serviceStatus.getStatusCode() +
				", Message: " + serviceStatus.getMessage());
	}
}
