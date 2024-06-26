package fr.fms.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Article implements Serializable{
	private static final long serialVersionUID = 1L;

	private static final int MAX_STRING_LENGTH = 30;
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String brand;
	private String description;
	private double price;
	
	
	@ManyToOne
	private Category category;
	
	public Article() {
	}

	public Article(String brand, String description, double price) {
		this.brand = brand;
		this.description = description;
		this.price = price;
	}

	public Article(Long id, String brand, String description, double price) {
		this.id = id;
		this.brand = brand;
		this.description = description;
		this.price = price;
	}

	public Article(String brand, String description, double price, Category category) {
		this.brand = brand;
		this.description = description;
		this.price = price;
		this.category = category;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	public static String centerString(String str) {
		if(str.length() >= MAX_STRING_LENGTH) return str;
		String dest = "                    ";
		int deb = (MAX_STRING_LENGTH - str.length())/2 ;
		String data = new StringBuilder(dest).replace( deb, deb + str.length(), str ).toString();
		return data;
	}

	@Override
	public String toString() {
		
		return centerString(String.valueOf(id)) + centerString(brand) + centerString(description) +centerString(String.valueOf(price)) + centerString(category.getName());
	}
	
	
	
}
