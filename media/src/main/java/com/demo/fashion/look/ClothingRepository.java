package com.demo.fashion.look;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "clothing", path = "clothing")
public interface ClothingRepository  extends PagingAndSortingRepository<Clothing, Long> {

}
