package fr.fms;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;

@SpringBootApplication
public class SpringShopJpaApplication implements CommandLineRunner{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ArticleRepository articleRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(SpringShopJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		//Exo 1.1
		//articleRepository.save(new Article((long) 1, "Samsung", "S9", 250));
		/*
		for(Article article : articleRepository.findByBrand("Samsung")) {
			System.out.println(article);
		}
		
		for(Article article :  articleRepository.findByBrandContains("sung")) {
			System.out.println(article);
		}
		for(Article article : articleRepository.findByBrandAndPrice("Samsung", 250)) {
			System.out.println(article);
		}
		for(Article article : articleRepository.findByBrandAndPriceGreaterThan("Samsung", 300)) {
			System.out.println(article);
		}
		for(Article article : articleRepository.searchArticles("sung", 200)) {
			System.out.println(article);
		}
		Category smartphone = categoryRepository.save(new Category("Smartphone"));
		Category tablet = categoryRepository.save(new Category("Tablet"));
		Category pc = categoryRepository.save(new Category("PC"));
		
		articleRepository.save(new Article("Samsung", "S10", 500, smartphone));
		articleRepository.save(new Article("Samsung", "S9", 350, smartphone));
		articleRepository.save(new Article("Xiaomi", "MI10", 100, smartphone));
		articleRepository.save(new Article("Samsung", "GalaxyTab", 450 , tablet));
		articleRepository.save(new Article("Apple", "Ipad", 350, tablet));
		articleRepository.save(new Article("Asus", "R510", 600, pc));
		for(Article article : articleRepository.findByCategoryId((long) 1)) {
			System.out.println(article);
		}
		*/

		//1.2
		/*
		Optional<Article> s10 = articleRepository.findById((long) 1);
		System.out.println(s10);
		for(Article article : articleRepository.findByBrandAndPrice("Apple", 350)) {
			System.out.println(article);
		}
		
		List<Article> articles = articleRepository.findAll();
		articles.forEach(System.out::println);
		 */
		
		//1.3
		/*
		for(Article article : articleRepository.findByBrandAndDescription("Samsung", "S10")) {
			System.out.println(article);
		}
		*/
		
		//1.4
		//articleRepository.save(new Article("Asus", "Rog", 700));
		//articleRepository.deleteById((long) 8);
		
		//1.5
		//Optional<Category> pc = categoryRepository.findById((long) 3);
		//System.out.println(categoryRepository.findById((long) 3).get().getId());
		//articleRepository.updateById((long) 9, "Msi", "Crosshair 17", 2000, pc);
		
		//1.6
		for(Category category : categoryRepository.findByOrderByNameAsc()) {
			System.out.println(category);
		}
		
		for(Category category : categoryRepository.findByOrderByNameDesc()) {
			System.out.println(category);
		}
		
		List<Article> articlesAsc = articleRepository.findAll(Sort.by(Sort.Direction.ASC, "categoryName"));
		List<Article> articlesDesc = articleRepository.findAll(Sort.by(Sort.Direction.DESC, "categoryName"));
		articlesAsc.forEach(System.out::println);
		System.out.println("-------------------");
		articlesDesc.forEach(System.out::println);
	}

	
}
