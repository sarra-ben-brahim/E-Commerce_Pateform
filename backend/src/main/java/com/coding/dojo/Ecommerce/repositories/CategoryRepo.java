package com.coding.dojo.Ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coding.dojo.Ecommerce.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Long> {
}