package com.omniverse.dukan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.omniverse.dukan.model.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {

}
