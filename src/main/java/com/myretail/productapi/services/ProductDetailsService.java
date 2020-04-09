package com.myretail.productapi.services;

import com.myretail.productapi.domain.ProductDetail;
import com.myretail.productapi.domain.ProductDetailResponse;

public interface ProductDetailsService {
    public Integer insertProductDetail(ProductDetail productDetail);
    public ProductDetail updateProductDetail(Integer id, ProductDetail productDetail);
    public ProductDetailResponse getProductDetail(Integer id);
}
