package funShop.domain.dto;

import java.util.List;

public class CategoryDTO {
	private Long Id;
	private String name;
	private List<Long> product_id;

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

	public List<Long> getProduct_id() {
		return product_id;
	}

	public void setProduct_id(List<Long> product_id) {
		this.product_id = product_id;
	}

	public CategoryDTO() {
	}

	public CategoryDTO(Long id, String name, List<Long> product_id) {
		Id = id;
		this.name = name;
		this.product_id = product_id;
	}
}
