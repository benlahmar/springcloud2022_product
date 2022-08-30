package com.narsa.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.narsa.entities.Categorie;
import com.narsa.repositories.CategorieRepository;
import com.narsa.repositories.ProduitRepo;

@RestController
public class CatalogueA {

	@Autowired
	CategorieRepository crepo;
	@Autowired
	ProduitRepo prepo;
	
	
	@GetMapping("/hi")
	public String hello()
	{
		return "hi";
	}
	
	@GetMapping("/add/{a}/{y}")
	public int add(@PathVariable(name = "a") int x,@PathVariable int y)
	{
		return x+y;
	}
	
	@GetMapping("/categories")
	public List<Categorie> allcat()
	{
		return crepo.findAll();
	}
}
