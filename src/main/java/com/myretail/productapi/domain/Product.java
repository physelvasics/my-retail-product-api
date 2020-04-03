package com.myretail.productapi.domain;

/**
 * This represents Product data from Product service.
 *
 * @author Selvaraj Karuppusamy
 */
public class Product {

    private Item item;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
