package com.narsa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.narsa.entities.Categorie;
import com.narsa.entities.Produit;

public interface CategorieRepository 
		extends JpaRepository<Categorie, Long>{

}
