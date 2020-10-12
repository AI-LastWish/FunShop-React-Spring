package funShop.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Product {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	@NotBlank(message = "Product name is required")
	private String name;
	private String image;
	private String description;
	private double price;
	@NotBlank(message = "In stock number is required")
	private int countInStock;
	private double rating;
	private int numReviews;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "brand_id", updatable = false, nullable = false)
	@JsonIgnore
	private Brand brand;
	@ManyToMany
	@JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	@JsonIgnore
	private List<Category> categories;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public int getCountInStock() {
		return countInStock;
	}

	public void setCountInStock(int countInStock) {
		this.countInStock = countInStock;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getNumReviews() {
		return numReviews;
	}

	public void setNumReviews(int numReviews) {
		this.numReviews = numReviews;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public List<Category> getCategories() {
		return categories;
	}

	public void setCategories(List<Category> categories) {
		this.categories = categories;
	}

	public Product() {
	}

	public Product(Long id, @NotBlank(message = "Product name is required") String name, String image,
			String description, double price, @NotBlank(message = "In stock number is required") int countInStock,
			double rating, int numReviews, Brand brand, List<Category> categories) {
		Id = id;
		this.name = name;
		this.image = image;
		this.description = description;
		this.price = price;
		this.countInStock = countInStock;
		this.rating = rating;
		this.numReviews = numReviews;
		this.brand = brand;
		this.categories = categories;
	}
}