package fr.fms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.fms.entities.Article;

/**
 * Interface permettant l'envoie des requêtes
 */
public interface ArticleRepository extends JpaRepository<Article, Long>{
	
	/**
	 * Méthode qui permet de retrouver tous les articles d'une marque
	 * @param marque des articles
	 * @return la liste des articles de la marque spécifiée
	 */
	public List<Article> findByBrand(String brand);
	
	/**
	 * Méthode permettant de lister les articles qui contiennent un mot
	 * @param marque ou mot clé
	 * @return la liste des articles filtrée
	 */
	public List<Article> findByBrandContains(String brand);
	
	/**
	 * Méthode permettant de lister les articles en fonction de la marque et du prix
	 * @param marque des articles
	 * @param prix des articles
	 * @return la liste des articles filtrée
	 */
	public List<Article> findByBrandAndPrice(String brand, double price);
	
	/**
	 * Méthode permettant de lister les articles en fonction de la marque et du prix supérieur au prix spécifié
	 * @param marque des articles
	 * @param prix minimum
	 * @return la liste des articles filtrée
	 */
	public List<Article> findByBrandAndPriceGreaterThan(String brand, double price);
	
	/**
	 * Méthode pour gérer les cas particuliers d'une recherche d'article
	 * @param mot clé
	 * @param prix minimum
	 * @return la liste des articles filtrée
	 */
	@Query("select A from Article A where A.brand like %:x% and A.price > :y")
	public List<Article> searchArticles(@Param("x") String kw, @Param("y") double price);
	
	public List<Article> findByCategoryId(Long categoryId);
}
