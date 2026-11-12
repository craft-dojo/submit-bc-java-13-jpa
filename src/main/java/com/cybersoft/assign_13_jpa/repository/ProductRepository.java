package com.cybersoft.assign_13_jpa.repository;

import com.cybersoft.assign_13_jpa.entity.CategoryEntity;
import com.cybersoft.assign_13_jpa.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {

    boolean existsByName(String name);

    Optional<ProductEntity> findByName(String name);

    List<ProductEntity> findByCategory(CategoryEntity category);
}
