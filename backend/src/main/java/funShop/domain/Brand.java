package funShop.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Brand {

	@javax.persistence.Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	@NotBlank(message = "Brand name is required")
	private String name;
	@OneToMany(cascade = CascadeType.REFRESH, mappedBy = "brand", orphanRemoval = true)
	@JsonIgnore
	private List<Product> products;

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

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Brand() {
	}

	public Brand(Long id, @NotBlank(message = "Brand name is required") String name, List<Product> products) {
		Id = id;
		this.name = name;
		this.products = products;
	}

}
