package com.demo.fashion.look;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClothingRepository  extends JpaRepository<Clothing, Long> {

}
