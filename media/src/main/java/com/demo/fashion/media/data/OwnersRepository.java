package com.demo.fashion.media.data;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "owners", path = "owner")
public interface OwnersRepository extends PagingAndSortingRepository<Owner, Long> {
}
