package com.narsa;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.narsa.entities.Categorie;
import com.narsa.entities.Produit;
import com.narsa.repositories.CategorieRepository;
import com.narsa.repositories.ProduitRepo;


@SpringBootApplication
public class ProductApplication implements CommandLineRunner{

	@Autowired
	CategorieRepository crepo;
	@Autowired
	ProduitRepo prepo;
	
	public static void main(String[] args) {
		
		SpringApplication.run(ProductApplication.class, args);
	}
	
	@Bean
	public HttpTraceRepository httptrace()
	{
		return new InMemoryHttpTraceRepository();
	}

	
	
	@Override
	public void run(String... args) throws Exception {
//		Categorie c=new Categorie();
//		c.setLibelle("abc");
//		crepo.save(c);
		
		
//		Produit p=new Produit();
//		p.setDescription("descr");
//		p.setDesg("RAM2");
//		p.setPrix(100);
//		p.setQuantite(10);
//		Optional<Categorie> oc = crepo.findById(1L);
//		Categorie c=oc.get();
//		c.getProduits().add(p);
//		p.setCategorie(c);
//		crepo.save(c);
//		//prepo.save(p);
		
		int nb=prepo.findByQuantiteLessThan(12).size();
		System.out.println(nb);
	}

}
