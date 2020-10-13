package funShop.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import funShop.domain.Brand;
import funShop.repositories.BrandRepository;
import funShop.services.IBrandQueryService;

@Service
public class BrandQueryService implements IBrandQueryService {

	@Autowired
	private BrandRepository brandRepository;

	@Override
	public Iterable<Brand> getAllBrands() {
		return brandRepository.findAll();
	}

	@Override
	public Brand getBrand(Long id) {
		return brandRepository.findById(id).get();
	}

}
