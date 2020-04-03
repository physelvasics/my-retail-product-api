package com.myretail.productapi.services;

import com.myretail.productapi.config.ProductRestClient;
import com.myretail.productapi.dao.ProductDetailsRepository;
import com.myretail.productapi.domain.Product;
import com.myretail.productapi.domain.ProductDetail;
import com.myretail.productapi.domain.ProductDetailResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * This service acts as layer between controller and DAO and poses business logic to store and retrieve data
 *  to and from various resources like DB and product service.
 *
 *  @author Selvaraj Karuppusamy
 */
@Service
public class ProductDetailsServiceImpl implements ProductDetailsService {

    Logger log = LoggerFactory.getLogger(ProductDetailsServiceImpl.class);

    @Autowired
    private ProductDetailsRepository productDetailsRepository;

    @Autowired
    private ProductRestClient productRestClient;

    /**
     * This method will update product detail object to database
     * @param productDetail
     *
     */
    @Override
    public void updateProductDetail(ProductDetail productDetail) {
        productDetailsRepository.save(productDetail);
        log.info("Updated product detail, id={}", productDetail.getId());
    }

    /**
     * This method will retrieve data from database and redsky rest service and make ProductDetailResponse.
     * @param id
     * @return ProductDetailResponse
     */
    @Override
    public ProductDetailResponse getProductDetail(Integer id) {

        ProductDetail productDetail = productDetailsRepository.findById(id).orElse(null);
        Product product = productRestClient.getProductById(id);

        return ProductDetailResponseFactory.produce(id, productDetail, product);
    }

}
