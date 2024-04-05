package fr.fms.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import fr.fms.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	public List<Category> findByOrderByNameAsc();
	
	public List<Category> findByOrderByNameDesc();
	
	public void deleteById(Long id);
	
	@Transactional
	@Modifying
	@Query("update Category C set C.name = :name where C.id = :id")
	public void updateById(@Param("id") Long id, @Param("name") String name);

}
