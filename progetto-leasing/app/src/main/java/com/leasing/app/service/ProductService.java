package com.leasing.app.service;

import com.leasing.app.dto.ProductDTO;
import com.leasing.app.model.Product;
import com.leasing.app.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ProductService {

        private final ProductRepository productRepository;

        public ProductService(ProductRepository productRepository) {
            this.productRepository = productRepository;
        }

        public List<Product> getAllProducts() {
            return productRepository.findAll();
        }

        public List<Product> getFastSearch(
                String name,
                String model,
                String organization,
                String agency) {
            return productRepository.fastSearchByProductValue(name, model, organization, agency);
        }

        public Product getProductById(Long id) {
            return productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
        }

        public Product createProduct(ProductDTO productDTO) {
            Product product = new Product();
            product.setName(productDTO.getName() != null ? productDTO.getName() : null);
            product.setModel(productDTO.getAgency() != null ? productDTO.getModel() : null);
            product.setAgency(productDTO.getAgency() != null ? productDTO.getAgency() : null);
            product.setOrganization(productDTO.getOrganization() != null ? productDTO.getOrganization() : null);
            return productRepository.save(product);
        }

        public Product updateProduct(Long id, ProductDTO productDto) {
            Product currentProduct = productRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));

            if (productDto.getName() != null) currentProduct.setName(productDto.getName());
            if (productDto.getModel() != null) currentProduct.setModel(productDto.getModel());
            if (productDto.getOrganization() != null) currentProduct.setOrganization(productDto.getOrganization());
            if (productDto.getAgency() != null) currentProduct.setAgency(productDto.getAgency());
            if (productDto.getContactInfo() != null) currentProduct.setContactInfo(productDto.getContactInfo());

            return productRepository.save(currentProduct);
        }

        public void deleteProduct(Long id) {
            if (!productRepository.existsById(id)) {
                throw new EntityNotFoundException("Product not found with id: " + id);
            }
            productRepository.deleteById(id);
        }
}

