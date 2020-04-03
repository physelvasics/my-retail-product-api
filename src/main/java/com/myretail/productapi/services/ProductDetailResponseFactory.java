package com.myretail.productapi.services;

import com.myretail.productapi.domain.Product;
import com.myretail.productapi.domain.ProductDetail;
import com.myretail.productapi.domain.ProductDetailResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This factory class is to produce ProductDetailResponse
 *
 * @author Selvaraj Karuppusamy
 */

public final class ProductDetailResponseFactory {

    private static Logger log = LoggerFactory.getLogger(ProductDetailResponseFactory.class);


    /**
     * This static method will produce ProductDetailResponse.
     * @param id
     * @param productDetail
     * @param product
     * @return ProductDetailResponse
     *
     * @author Selvaraj Karuppusamy
     */
    public static ProductDetailResponse produce(Integer id, ProductDetail productDetail, Product product){
        ProductDetailResponse productDetailResponse = null;

        if(productDetail != null){
            productDetailResponse = new ProductDetailResponse();
            productDetailResponse.setId(id);
            productDetailResponse.setCurrentPrice(productDetail.getCurrentPrice());
        } else {
            log.info("Price information not found, id={}", id);
        }

        if(product != null){
            if(productDetailResponse == null){
                productDetailResponse = new ProductDetailResponse();
                productDetailResponse.setId(id);
            }
            try{
                productDetailResponse.setName(product.getItem().getProductDescription().getTitle());
            } catch (NullPointerException ex){
                log.warn("Product endpoint does not have name, id={}", id);
            }
        }else {
            log.info("Product not found in redsky, id={}", id);
        }

        return productDetailResponse;
    }
}
