package com.myretail.productapi.domain;

/**
 * This domain represents response from product service.
 *
 * @author Selvaraj Karuppusamy
 */
public class ProductResponse {

    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
