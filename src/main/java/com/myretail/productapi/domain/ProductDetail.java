package com.myretail.productapi.domain;

import com.datastax.driver.core.DataType;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

/**
 * This represents the cassandra table product_detail.
 *
 * @author Selvaraj Karuppusamy
 */
@Table("product_detail")
@ProductDetailConstraint
public class ProductDetail {

    /**
     * This is the primary column of the table which represents product id of Integer type and this cannot be NULL.
     *
     * ex: 13860428
     */
    @PrimaryKey
    @CassandraType(type = DataType.Name.INT)
    private Integer id;

    /**
     * This String type field contains current price and currency code of the item.
     *
     * ex: {"value":15.49,"currency_code":"USD"}
     */
    @Column("current_price")
    private Price currentPrice;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonGetter("current_price")
    public Price getCurrentPrice() {
        return currentPrice;
    }

    @JsonSetter("current_price")
    public void setCurrentPrice(Price currentPrice) {
        this.currentPrice = currentPrice;
    }
}
