package com.narsa.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	@PostMapping("/categories")
	public ResponseEntity<Categorie> addcat(@RequestBody Categorie c)
	{
		Categorie v = crepo.findByLibelle(c.getLibelle());
		if(v!=null)
		{
			return new ResponseEntity<Categorie>(HttpStatus.NOT_ACCEPTABLE);
		}else
		{
		c= crepo.save(c);
		return new ResponseEntity<Categorie>(c,HttpStatus.CREATED);
		}
	}
	
	@GetMapping("/categories/{id}")
	public ResponseEntity<Categorie> findcat(@PathVariable long id)
	{
		Optional<Categorie> op = crepo.findById(id);
		if(op.isPresent())
			return new ResponseEntity<Categorie>(op.get(),HttpStatus.FOUND);
		else
			return new ResponseEntity<Categorie>(HttpStatus.NO_CONTENT);
	}
}
