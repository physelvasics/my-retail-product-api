package com.myretail.productapi.domain;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.UserDefinedType;

import java.io.Serializable;
import java.util.Objects;

@UserDefinedType("current_price")
public class Price  {

    @Column("value")
    private Double value;

    @Column("currency_code")
    private String currencyCode;

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @JsonGetter("currency_code")
    public String getCurrencyCode() {
        return currencyCode;
    }

    @JsonSetter("currency_code")
    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Price price = (Price) o;
        return value.equals(price.value) &&
                currencyCode.equals(price.currencyCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value, currencyCode);
    }
}
