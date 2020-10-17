package funShop.services;

import funShop.domain.Brand;

public interface IBrandCommandService {

	Brand saveOrUpdate(Brand brand);

	void deleteBrand(Long id) throws Exception;
}
