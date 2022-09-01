package com.narsa.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.narsa.entities.Categorie;
import com.narsa.entities.Produit;
import com.narsa.repositories.CategorieRepository;
import com.narsa.repositories.ProduitRepo;

@RestController
public class CatalogueA {

	@Autowired
	CategorieRepository crepo;
	@Autowired
	ProduitRepo prepo;
	
	
	@GetMapping("/info")
	public String info()
	{
		return "api de gestion des catalogues";
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

	@PostMapping("categories/{id}/produits")
	public ResponseEntity<Produit> addprd(@RequestBody Produit p,@PathVariable long id)
	{
		Optional<Categorie> op = crepo.findById(id);
		if(op.isPresent())
		{
			Categorie c = op.get();
			p.setCategorie(c);
			//posibilite1
//			c.getProduits().add(p);
//			crepo.save(c);
			
			//po2
			p=prepo.save(p);
			return new ResponseEntity<Produit>(p,HttpStatus.CREATED);
			
		}else
		{
			return new ResponseEntity<Produit>(HttpStatus.NO_CONTENT);
		}
		}

	@GetMapping("produits/search")
	public Page<Produit> findbykeyword(@RequestParam(name = "q") String des,@RequestParam int p, @RequestParam int size)
	{
		Pageable page=PageRequest.of(p, size);
		Page<Produit> pp = prepo.findByDesgOrDescriptionLike(des, des, page);
		return pp;
	}
	
	@GetMapping("/categories/{idcat}/produits")
	public ResponseEntity<List<Produit>> prd4cat(@PathVariable long idcat)
	{
		Optional<Categorie> op = crepo.findById(idcat);
		if(op.isPresent())
		{
			return new ResponseEntity<List<Produit>>(op.get().getProduits(),HttpStatus.FOUND);
		}else
		{
			return new ResponseEntity<List<Produit>>(HttpStatus.NO_CONTENT);
		}
	}
}
