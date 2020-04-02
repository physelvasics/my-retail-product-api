package com.myretail.productapi.domain;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

/**
 * This represents item details on Product.
 */
public class Item {

    private ProductDescription productDescription;

    @JsonGetter("product_description")
    public ProductDescription getProductDescription() {
        return productDescription;
    }

    @JsonSetter("product_description")
    public void setProductDescription(ProductDescription productDescription) {
        this.productDescription = productDescription;
    }
}
