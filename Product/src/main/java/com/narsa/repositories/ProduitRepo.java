package com.narsa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.narsa.entities.Produit;

public interface ProduitRepo  extends JpaRepository<Produit, Long>{

	List<Produit> findByQuantiteLessThan(int qte);
}
