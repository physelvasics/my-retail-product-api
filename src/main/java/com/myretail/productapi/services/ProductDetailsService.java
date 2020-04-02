package com.myretail.productapi.services;

import com.myretail.productapi.domain.ProductDetail;
import com.myretail.productapi.domain.ProductDetailResponse;

public interface ProductDetailsService {


    public void updateProductDetail(ProductDetail productDetail);
    public ProductDetailResponse getProductDetail(Integer id);
}
