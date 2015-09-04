package de.kstm.haushalt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import de.kstm.haushalt.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
