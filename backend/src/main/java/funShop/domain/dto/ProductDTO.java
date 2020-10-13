package funShop.domain.dto;

import java.util.List;

public class ProductDTO {

	private Long id;
	private String name;
	private String image;
	private String description;
	private double price;
	private int countInStock;
	private double rating;
	private int numReviews;
	private Long brand_id;
	private List<Long> category_id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Long getBrand_id() {
		return brand_id;
	}

	public void setBrand_id(Long brand_id) {
		this.brand_id = brand_id;
	}

	public List<Long> getCategory_id() {
		return category_id;
	}

	public void setCategory_id(List<Long> category_id) {
		this.category_id = category_id;
	}

	public ProductDTO() {
	}

	public ProductDTO(Long id, String name, String image, String description, double price, int countInStock,
			double rating, int numReviews, Long brand_id, List<Long> category_id) {
		this.id = id;
		this.name = name;
		this.image = image;
		this.description = description;
		this.price = price;
		this.countInStock = countInStock;
		this.rating = rating;
		this.numReviews = numReviews;
		this.brand_id = brand_id;
		this.category_id = category_id;
	}

}
