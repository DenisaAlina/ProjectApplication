package org.fasttrackit.ProjectApp.repository;

import org.fasttrackit.ProjectApp.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {
}
