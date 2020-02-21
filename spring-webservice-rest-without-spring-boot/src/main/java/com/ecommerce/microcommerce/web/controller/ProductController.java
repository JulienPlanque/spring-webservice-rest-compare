package com.ecommerce.microcommerce.web.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ecommerce.microcommerce.dao.ProductDao;
import com.ecommerce.microcommerce.model.Product;
import com.ecommerce.microcommerce.web.exception.ProduitIntrouvableException;

@RestController
public class ProductController {

	@Autowired
	private ProductDao productDao;

	@GetMapping(value = "/produits")
	public List<Product> produits() {
		List<Product> products = productDao.findAll();
		return products;
	}

	@GetMapping(value = "/produits/{id}")
	public Product afficherProduits(@PathVariable int id) {

		Product product = productDao.findById(id);
		if (product == null) {
			throw new ProduitIntrouvableException("Le produit avec l'id " + id + " est introuvable.");
		}
		return product;
	}

	@PostMapping(value = "/produits")
	public ResponseEntity<Void> ajouterProduit(@Valid @RequestBody Product product) {
		Product produit = productDao.save(product);
		if (produit == null) {
			return ResponseEntity.noContent().build();
		}

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produit.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

	@DeleteMapping(value = "/produits/{id}")
	public void supprimerProduit(@PathVariable int id) {
		productDao.deleteById(id);
	}

	@PutMapping(value = "/produits")
	public void updateProduit(@RequestBody Product product) {
		productDao.save(product);
	}
}
