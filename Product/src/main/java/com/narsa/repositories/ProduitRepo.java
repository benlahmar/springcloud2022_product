package com.narsa.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.narsa.entities.Produit;

public interface ProduitRepo  extends JpaRepository<Produit, Long>{

	List<Produit> findByQuantiteLessThan(int qte);
	Page<Produit>	findByDesgOrDescriptionLike(String des,String desc, Pageable page);
	List<Produit> findByCategorieID(long id);
}
