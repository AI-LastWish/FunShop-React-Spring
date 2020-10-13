package funShop.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import funShop.domain.dto.ProductDTO;

@Entity
public class Product {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	@NotBlank(message = "Product name is required")
	private String name;
	private String image;
	private String description;
	@Min(value = 0, message = "Price cannot be less than 0")
	private double price;
	@Min(value = 0, message = "Count in stock cannot be less than 0")
	private int countInStock;
	@Min(value = 0, message = "Rating cannot be less than 0")
	private double rating;
	@Min(value = 0, message = "Number of reviews cannot be less than 0")
	private int numReviews;

	@ManyToOne
	@JoinColumn(name = "brand_id", updatable = false, nullable = false)
	
	private Brand brand;
	@ManyToMany
	@JoinTable(name = "product_category", joinColumns = @JoinColumn(name = "product_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
	
	private List<Category> categories;

	public Product dtoToProduct(ProductDTO productDto, Brand brand, List<Category> categories) {
		
		this.Id = productDto.getId();
		this.name = productDto.getName();
		this.image = productDto.getImage();
		this.description = productDto.getDescription();
		this.price = productDto.getPrice();
		this.countInStock = productDto.getCountInStock();
		this.rating = productDto.getRating();
		this.numReviews = productDto.getNumReviews();
		this.brand = brand;
		this.categories = categories;

		return this;
	}

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
			String description, double price, int countInStock, double rating, int numReviews, Brand brand,
			List<Category> categories) {
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
