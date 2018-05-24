package com.demo.fashion.look;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "collection", path = "collection")
public interface OutfitRepository extends PagingAndSortingRepository<Outfit, Long> {
}
