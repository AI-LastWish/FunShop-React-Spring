package funShop.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import funShop.domain.Brand;
import funShop.repositories.BrandRepository;
import funShop.services.IBrandCommandService;

@Service
public class BrandCommandService implements IBrandCommandService {

	@Autowired
	private BrandRepository brandRepository;

	@Override
	public Brand saveOrUpdate(Brand brand) {
		return brandRepository.save(brand);
	}

	@Override
	public void deleteBrand(Long id) {

		var brand = brandRepository.findById(id).get();
		
		brandRepository.delete(brand);
	}

}
