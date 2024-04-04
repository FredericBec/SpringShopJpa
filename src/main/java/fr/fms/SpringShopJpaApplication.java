package fr.fms;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

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
	
	private static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		SpringApplication.run(SpringShopJpaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Bienvenue dans mon application de gestion d'articles");
		int choice = 0;
		while(choice != 13) {
			displayMenu();
			choice = getInt();
			switch(choice) {
			case 1 :
				displayArticles();
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
			case 11:
			case 12:
			default:
				System.out.println("Veuillez saisir une valeur comprise entre 1 et 12");
			}
		}
		
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
		/*
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
		 */
		
		//1.7
		/*
		for(Article article : articleRepository.findByBrandNotLikeOrderByBrand("Samsung")) {
			System.out.println(article);
		}
		*/
	}
	
	public static void displayMenu() {
		System.out.println("1 : Afficher tous les articles sans pagination");
		System.out.println("2 : Afficher tous les articles avec pagination");
		System.out.println("*************************");
		System.out.println("3 : Ajouter un article");
		System.out.println("4 : Afficher un article");
		System.out.println("5 : Supprimer un article");
		System.out.println("6 : Modifier un article");
		System.out.println("*************************");
		System.out.println("7 : Ajouter une catégorie");
		System.out.println("8 : Afficher une catégorie");
		System.out.println("9 : Supprimer une catégorie");
		System.out.println("10: Mettre à jour une catégorie");
		System.out.println("11: Afficher tous les articles d'une catégorie");
		System.out.println("*************************");
		System.out.println("12: Sortir du programme");
	}
	
	public void displayArticles() {
		System.out.println("EXIT  pour revenir au menu précédent");
		List<Article> articles = articleRepository.findAll();
		articles.forEach(System.out::println);
		scan.next();
		String response = scan.nextLine();
		if(response.equalsIgnoreCase("EXIT")) {
			displayMenu();
		}
	}
	
	public void displayPageableArticles() {
		
	}
	
	public Article displayArticle(Long id) {
		Article article = null;
		return article;
	}
	
	
	public static int getInt() {
		if(!scan.hasNextInt()) {
			System.out.println("Saississez une valeur entière !!");
			scan.next();
		}
		
		return scan.nextInt();
	} 

}
