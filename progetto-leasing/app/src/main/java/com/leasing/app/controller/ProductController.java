package com.leasing.app.controller;

import com.leasing.app.dto.ProductDTO;
import com.leasing.app.model.Product;
import com.leasing.app.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

        private final ProductService productService;

        public ProductController(ProductService productService) {
            this.productService = productService;
        }

        @GetMapping
        public List<Product> getProducts() {
            return productService.getAllProducts();
        }

        @GetMapping("/search")
        public List<Product> fastSearch(
                @RequestParam String name,
                @RequestParam String model,
                @RequestParam String organization,
                @RequestParam String agency) {
            return productService.getFastSearch(name, model, organization, agency);
        }

        @GetMapping("/{id}")
        public Product getProduct(@PathVariable Long id) {
            return productService.getProductById(id);
        }

        @PostMapping
        public ResponseEntity<Product> createProduct(@RequestBody ProductDTO productDTO) throws URISyntaxException {
            Product savedProduct = productService.createProduct(productDTO);
            return ResponseEntity.created(new URI("/products/" + savedProduct.getId())).body(savedProduct);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody ProductDTO productDto) {
            Product updatedProduct = productService.updateProduct(id, productDto);
            return ResponseEntity.ok(updatedProduct);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
            productService.deleteProduct(id);
            return ResponseEntity.ok().build();
        }
    }

