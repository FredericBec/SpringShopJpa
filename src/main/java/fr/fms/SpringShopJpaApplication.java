package fr.fms;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
	private int pageDefault = 0;
	private int pageSize = 5;
	
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
				exitMenu();
				break;
			case 2:
				displayPageableArticles(pageDefault, pageSize);
				break;
			case 3:
				addArticle();
				break;
			case 4:
				displayArticle();
				exitMenu();
				break;
			case 5:
				deleteArticle();
				break;
			case 6:
				updateArticle();
				break;
			case 7:
				addCategory();
				break;
			case 8:
				displayCategory();
				exitMenu();
				break;
			case 9:
				deleteCategory();
				break;
			case 10:
				updateCategory();
				break;
			case 11:
				displayArticlesByCategory();
				exitMenu();
				break;
			case 12:
				System.exit(0);
				break;
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
		headerArticles();
		List<Article> articles = articleRepository.findAll();
		articles.forEach(System.out::println);
	}
	
	public void displayPageableArticles(int pageNumber, int pageSize) {
		headerArticles();
		Page<Article> page = articleRepository.findAll(PageRequest.of(pageNumber, pageSize));
		
		List<Article> articles = page.getContent();
		articles.forEach(System.out::println);
		
		System.out.println("PREV [ " + (pageNumber + 1) + " sur " + page.getTotalPages() + " ] NEXT");
		scan.nextLine();
		System.out.println("EXIT  pour sortir de la pagination");
		System.out.println("PREV  pour afficher la page précédente");
		System.out.println("NEXT  pour afficher la page suivante");
		String choice = scan.nextLine().toUpperCase();
		switch(choice) {
		case "EXIT":
			displayMenu();
			break;
		case "PREV":
			if(pageNumber > 0) {
				displayPageableArticles(pageNumber - 1, pageSize);
				scan.nextLine();
			}else {
				System.out.println("Vous êtes déjà sur la première page");
			}
			break;
		case "NEXT":
			if(pageNumber < page.getTotalPages() -1) {
				displayPageableArticles(pageNumber + 1, pageSize);
				scan.nextLine();
			}else {
				System.out.println("Vous êtes déjà sur la dernière page.");
			}
			break;
		default:
			System.out.println("Choix invalide");
			break;
		}
		
		
	}
	
	public void addArticle() {
		scan.nextLine();
		System.out.println("Saisir la marque de l'article");
		String brand = scan.nextLine();
		System.out.println("Saisir la description de l'article");
		String description = scan.nextLine();
		System.out.println("Saisir le prix de l'article");
		double price = getInt();
		displayCategories();
		System.out.println("Saisir l'id de la catégorie de l'article");
		Long categoryId = (long) getInt();
		Category category = categoryRepository.getById(categoryId);
		articleRepository.save(new Article(brand, description, price, category));
	}
	
	public void displayArticle() {
		displayArticles();
		System.out.println("Saisir l'id de l'article");
		Long articleId = (long) getInt();
		Optional<Article> article = articleRepository.findById(articleId);
		System.out.println(article);
	}
	
	public void deleteArticle() {
		displayArticles();
		System.out.println("Saisir l'id de l'article à supprimer");
		Long articleId = (long) getInt();
		Optional<Article> article = articleRepository.findById(articleId);
		if(article.isPresent()) {
			System.out.println("Voulez supprimer l'article " + article.get().getBrand() + " " 
					+ article.get().getDescription() + " ?(Oui/Non)");
			scan.nextLine();
			String response = scan.nextLine();
			if(response.equalsIgnoreCase("Oui")) {
				articleRepository.deleteById(articleId);
			}else {
				displayMenu();
			}			
		}else {
			System.out.println("Cet article n'existe pas !");
		}
	}
	
	public void updateArticle() {
		displayArticles();
		System.out.println("Saisir l'id de l'article à modifier");
		Long articleId = (long) getInt();
		scan.nextLine();
		System.out.println("Saisir la marque");
		String brand = scan.nextLine();
		System.out.println("Saisir la description");
		String description = scan.nextLine();
		System.out.println("Saisir le prix");
		double price = getInt();
		
		articleRepository.updateById(articleId, brand, description, price);
	}
	
	public void displayCategories() {
		headerCategories();
		List<Category> categories = categoryRepository.findAll();
		categories.forEach(System.out::println);
	}
	
	public Optional<Category> getCategory() {
		System.out.println("Saisir l'id de la categorie");
		Long catgoryId = (long) getInt();
		Optional<Category> category = categoryRepository.findById(catgoryId);
		return category;
	}
	
	public void addCategory() {
		scan.nextLine();
		System.out.println("Saisir le nom de la catégorie à ajouter");
		String name = scan.nextLine();
		categoryRepository.save(new Category(name));
	}
	
	public void displayCategory() {
		displayCategories();
		Optional<Category> category = getCategory();
		System.out.println(category);
	}
	
	public void deleteCategory() {
		displayCategories();
		System.out.println("Saisir l'id de la catégorie à supprimer");
		Long categoryId = (long) getInt();
		scan.nextLine();
		System.out.println("Voulez-vous supprimer la catégorie " + categoryRepository.findById(categoryId).get().getName() + " ?(Oui/Non)");
		String response = scan.nextLine();
		if(response.equalsIgnoreCase("Oui")){
			categoryRepository.deleteById(categoryId);
		}else {
			displayMenu();
		}
	}
	
	public void updateCategory() {
		displayCategories();
		System.out.println("Saisir l'id de la catégorie à modifier");
		Long categoryId = (long) getInt();
		scan.nextLine();
		System.out.println("Saisir le nom de la catégorie");
		String name = scan.nextLine();
		
		categoryRepository.updateById(categoryId, name);
	}
	
	public void displayArticlesByCategory() {
		displayCategories();
		System.out.println("Saisir l'id de la catégorie");
		Long categoryId = (long) getInt();
		List<Article> articles = articleRepository.findByCategoryId(categoryId);
		if(articles.isEmpty()) {
			System.out.println("Aucun article ne correspond à cette catégorie");
		}else {			
			articles.forEach(System.out::println);
		}
	}
	
	public void exitMenu() {
		System.out.println("EXIT  pour revenir au menu précédent");
		scan.nextLine();
		String response = scan.nextLine();
		if(response.equalsIgnoreCase("EXIT")) {
			displayMenu();
		}
	}
	
	public void headerArticles() {
		String header = String.format("%s%s%s%s%s", Article.centerString(15, "IDENTIFIANT"), Article.centerString(10, "MARQUE"), 
								Article.centerString(30, "DESCRIPTION"), Article.centerString(10, "PRIX"), Article.centerString(15, "CATEGORIE"));
		System.out.println(header);
	}
	
	public void headerCategories() {
		String header = String.format("%s%s", Category.centerString(15, "IDENTIFIANT"), Category.centerString(15, "NAME"));
		System.out.println(header);
	}
	
	public static int getInt() {
		if(!scan.hasNextInt()) {
			System.out.println("Saississez une valeur entière !!");
			scan.next();
		}
		
		return scan.nextInt();
	} 

}
