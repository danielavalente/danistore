package com.store.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.store.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{

}
