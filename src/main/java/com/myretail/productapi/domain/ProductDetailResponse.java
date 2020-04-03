package com.myretail.productapi.domain;

import java.util.Objects;

/**
 * This domain represents the final response of product detail.
 *
 * @author Selvaraj Karuppusamy
 */
public class ProductDetailResponse extends ProductDetail {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductDetailResponse)) return false;
        ProductDetailResponse that = (ProductDetailResponse) o;
        return getName().equals(that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }
}
