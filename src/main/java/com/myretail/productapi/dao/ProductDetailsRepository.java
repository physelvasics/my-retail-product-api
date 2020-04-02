package com.myretail.productapi.dao;

import com.myretail.productapi.domain.ProductDetail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailsRepository extends CrudRepository<ProductDetail, Integer> {

}
