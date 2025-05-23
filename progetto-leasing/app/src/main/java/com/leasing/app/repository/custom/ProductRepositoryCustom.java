package com.leasing.app.repository.custom;

import com.leasing.app.model.Product;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepositoryCustom {
    List<Product> fastSearchByProductValue (
          String name,
          String model,
          String organization,
          String agency
    );
}
