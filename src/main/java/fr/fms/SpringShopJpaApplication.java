package fr.fms;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;


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
		while(choice != 12) {
			displayMenu();
			choice = scanInt();
			switch(choice) {
			case 1 :
				displayArticles();
				break;
			case 2:
				displayPageableArticles(pageDefault, pageSize);
				break;
			case 3:
				addArticle();
				break;
			case 4:
				displayArticle();
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
	
	/**
	 * Méthode pour afficher le menu
	 */
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
	
	/**
	 * Méthode pour afficher la liste des articles en base avec centrage de la liste
	 */
	public void displayArticles() {
		System.out.println(Article.centerString("IDENTIFIANT") + Article.centerString("MARQUE") + Article.centerString("DESCRIPTION") + 
							Article.centerString("PRIX") + Article.centerString("CATEGORIE"));
		List<Article> articles = articleRepository.findAll();
		articles.forEach(System.out::println);
		scan.nextLine();
		exitMenu();
	}
	
	/**
	 * Méthode permettant d'afficher la liste des articles paginées
	 * @param nombre de pages
	 * @param taille de chaque page
	 */
	public void displayPageableArticles(int pageNumber, int pageSize) {
		scan.nextLine();
		System.out.println(Article.centerString("IDENTIFIANT") + Article.centerString("MARQUE") + Article.centerString("DESCRIPTION") + 
							Article.centerString("PRIX") + Article.centerString("CATEGORIE"));
		Page<Article> page = articleRepository.findAll(PageRequest.of(pageNumber, pageSize));
		
		List<Article> articles = page.getContent();
		articles.forEach(System.out::println);
		
		System.out.println("PREV [ " + (pageNumber + 1) + " sur " + page.getTotalPages() + " ] NEXT");
		System.out.println("EXIT  pour sortir de la pagination");
		System.out.println("PREV  pour afficher la page précédente");
		System.out.println("NEXT  pour afficher la page suivante");
		String choice = scan.nextLine();
		switch(choice.toUpperCase()) {
		case "EXIT":
			System.out.println("retour au menu principal");
			break;
		case "PREV":
			if(pageNumber > 0) {
				displayPageableArticles(pageNumber - 1, pageSize);
			}else {
				System.out.println("Vous êtes déjà sur la première page");
				stop();
				displayPageableArticles(pageNumber, pageSize);
			}
			break;
		case "NEXT":
			if(pageNumber < page.getTotalPages() -1) {
				displayPageableArticles(pageNumber + 1, pageSize);
			}else {
				System.out.println("Vous êtes déjà sur la dernière page.");
				stop();
				displayPageableArticles(pageNumber, pageSize);
			}
			break;
		default:
			System.out.println("Choix invalide");
			displayPageableArticles(pageNumber, pageSize);
			break;
		}
		
		
	}
	
	/**
	 * Méthode pour ajouter un article en base
	 */
	public void addArticle() {
		scan.nextLine();
		System.out.println("Saisir la marque de l'article");
		String brand = scan.nextLine();
		System.out.println("Saisir la description de l'article");
		String description = scan.nextLine();
		System.out.println("Saisir le prix de l'article");
		double price = scan.nextDouble();
		displayCategories();
		System.out.println("Saisir l'id de la catégorie de l'article");
		Long categoryId = (long) scanInt();
		Category category = categoryRepository.getById(categoryId);
		articleRepository.save(new Article(brand, description, price, category));
	}
	
	/**
	 * Méthode pour afficher un article
	 */
	public void displayArticle() {
		displayArticles();
		Optional<Article> article = getArticle();
		if(article.isPresent()) {
			System.out.println(article);
			exitMenu();
		}else {
			System.out.println("cet article n'existe pas !");
		}
	}
	
	/**
	 * Méthode pour supprimer un article en base avec vérification
	 * que l'article est présent en base
	 */
	public void deleteArticle() {
		displayArticles();
		Optional<Article> article = getArticle();
		if(article.isPresent()) {
			System.out.println("Voulez supprimer l'article " + article.get().getBrand() + " " 
					+ article.get().getDescription() + " ?(Oui/Non)");
			scan.nextLine();
			String response = scan.nextLine();
			if(response.equalsIgnoreCase("Oui")) {
				articleRepository.deleteById(article.get().getId());
			}		
		}else {
			System.out.println("Cet article n'existe pas !");
		}
	}
	
	/**
	 * Méthode de mise à jour d'un article avec une vérification de sa présence en base
	 */
	public void updateArticle() {
		displayArticles();
		Optional<Article> article = getArticle();
		if(article.isPresent()) {
			scan.nextLine();
			System.out.println("Saisir la marque");
			String brand = scan.nextLine();
			System.out.println("Saisir la description");
			String description = scan.nextLine();
			System.out.println("Saisir le prix");
			double price = scan.nextDouble();
			
			articleRepository.updateById(article.get().getId(), brand, description, price);
			System.out.println("L'article " + article.get().getBrand() + " " + article.get().getDescription() + " a bien été modifié");
			stop();
		}else {
			System.out.println("cet article n'existe pas !");
			stop();
		}
		
	}
	
	/**
	 * Méthode qui permet de récupérer l'article en base en fonction de l'id
	 * @return l'article trouvé
	 */
	public Optional<Article> getArticle(){
		System.out.println("Saisir l'id de l'article");
		Long articleId = (long) scanInt();
		Optional<Article> article = articleRepository.findById(articleId);
		return article;
	}
	
	/**
	 * Méthode pour afficher toutes les catégories
	 */
	public void displayCategories() {
		System.out.println( Category.centerString("IDENTIFIANT") + Category.centerString("NAME"));
		List<Category> categories = categoryRepository.findAll();
		categories.forEach(System.out::println);
	}
	
	/**
	 * Méthode pour ajouter une catégorie avec vérification si le nom est déjà pris
	 */
	public void addCategory() {
		scan.nextLine();
		System.out.println("Saisir le nom de la catégorie à ajouter");
		String name = scan.nextLine();
		Optional<Category> category = categoryRepository.findByName(name);
		if(category.isPresent()) {
			System.out.println("ce nom est déjà pris");
			stop();
		}else {
			categoryRepository.save(new Category(name));			
			System.out.println("La catégorie a bien été créée");
		}
		
	}
	
	/**
	 * Méthode pour afficher une catégorie
	 */
	public void displayCategory() {
		displayCategories();
		Optional<Category> category = getCategory();
		System.out.println(category);
		exitMenu();
	}
	
	/**
	 * Méthode pour supprimer une catégorie en vérifiant sa présence en base
	 */
	public void deleteCategory() {
		displayCategories();
		Optional<Category> category = getCategory();
		if(category.isPresent()) {
			scan.nextLine();
			System.out.println("Voulez-vous supprimer la catégorie " + category.get().getName() + " ?(Oui/Non)");
			String response = scan.nextLine();
			if(response.equalsIgnoreCase("Oui")){
				categoryRepository.deleteById(category.get().getId());
				System.out.println("La catégorie " + category.get().getName() + " a bien été supprimée");
				stop();
			}		
		}else {
			System.out.println("cette catégorie n'existe pas !");
			stop();
		}
	}
	
	/**
	 * Méthode de mise à jour d'une catégorie avec une vérification que la catégorie
	 * est présente en base
	 */
	public void updateCategory() {
		displayCategories();
		Optional<Category> category = getCategory();
		if(category.isPresent()) {
			scan.nextLine();
			System.out.println("Saisir le nom de la catégorie");
			String name = scan.nextLine();
			categoryRepository.updateById(category.get().getId(), name);
			System.out.println("La catégorie " + category.get().getName() + " a bien été ajoutée");
			stop();
		}else {
			System.out.println("cette catégorie n'existe pas !");
			stop();
		}
	}
	
	/**
	 * Méthode pour afficher tous les articles d'une catégories
	 * et vérification si des articles appartiennent à une catégorie
	 */
	public void displayArticlesByCategory() {
		displayCategories();
		System.out.println("Saisir l'id de la catégorie");
		Long categoryId = (long) scanInt();
		List<Article> articles = articleRepository.findByCategoryId(categoryId);
		if(articles.isEmpty()) {
			System.out.println("Aucun article ne correspond à cette catégorie");
		}else{
			System.out.println(Article.centerString("IDENTIFIANT") + Article.centerString("MARQUE") + 
					Article.centerString("DESCRIPTION") + Article.centerString("PRIX") + Article.centerString("CATEGORIE"));
			articles.forEach(System.out::println);
		}
	}
	
	/**
	 * Méthode pour récupérer la catégorie en fonction de l'id
	 * @return catégorie
	 */
	public Optional<Category> getCategory() {
		System.out.println("Saisir l'id de la categorie");
		Long catgoryId = (long) scanInt();
		Optional<Category> category = categoryRepository.findById(catgoryId);
		return category;
	}
	
	/**
	 * Méthode pour revenir au menu principal
	 */
	public void exitMenu() {
		System.out.println("EXIT  pour revenir au menu précédent");
		String response = scan.nextLine();
		if(response.equalsIgnoreCase("EXIT")) {
			System.out.println("retour au menu principal");
		}
	}
	
	/**
	 * Méthode pour donner un temps d'attente afin de lire les infos
	 */
	public void stop() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Méthode de vérification de saisie d'un entier
	 * @return 
	 */
	public static int scanInt() {
		while(!scan.hasNextInt()) {
			System.out.println("Saississez une valeur entière !!");
			scan.next();
		}
		
		return scan.nextInt();
	} 
}
