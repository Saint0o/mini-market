package ru.geekbrains.mini.market.geekbrainsminimarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.geekbrains.mini.market.geekbrainsminimarket.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
