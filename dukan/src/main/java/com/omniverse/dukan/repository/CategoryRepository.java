package com.omniverse.dukan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omniverse.dukan.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

	Category findByName(String name);

	boolean exitsByName(String name);

	

}
