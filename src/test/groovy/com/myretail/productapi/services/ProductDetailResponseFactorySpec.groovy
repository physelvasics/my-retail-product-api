package com.myretail.productapi.services

import com.myretail.productapi.domain.Item
import com.myretail.productapi.domain.Price
import com.myretail.productapi.domain.Product
import com.myretail.productapi.domain.ProductDescription
import com.myretail.productapi.domain.ProductDetail
import com.myretail.productapi.domain.ProductDetailResponse
import spock.lang.Specification

class ProductDetailResponseFactorySpec extends Specification{

    def "Valid productDetail and product parameter" (){
        given:
        Product product = new Product(item: new Item(productDescription: new ProductDescription(title: "Sample item Desc")))
        ProductDetail productDetail = new ProductDetail(id: 1234, currentPrice: new Price(value: 10, currencyCode: "USD"))

        when:
        ProductDetailResponse response = ProductDetailResponseFactory.produce(1234, productDetail, product)

        then:
        response!=null
        response.id == 1234
        response.currentPrice == productDetail.currentPrice
        response.getName() == product.item.productDescription.title
    }

    def "Valid productDetail and no product name parameter" (){
        given:
        Product product = new Product(item: new Item(productDescription: new ProductDescription()))
        ProductDetail productDetail = new ProductDetail(id: 1234, currentPrice: new Price(value: 10, currencyCode: "USD"))

        when:
        ProductDetailResponse response = ProductDetailResponseFactory.produce(1234, productDetail, product)

        then:
        response!=null
        response.id == 1234
        response.currentPrice == productDetail.currentPrice
        response.getName() == null
    }

    def "Valid productDetail and null product parameter" (){
        given:
        ProductDetail productDetail = new ProductDetail(id: 1234, currentPrice: new Price(value: 10, currencyCode: "USD"))

        when:
        ProductDetailResponse response = ProductDetailResponseFactory.produce(1234, productDetail, null)

        then:
        response!=null
        response.id == 1234
        response.currentPrice == productDetail.currentPrice
        response.getName() == null
    }

    def "Null productDetail and valid product parameter" (){
        given:
        Product product = new Product(item: new Item(productDescription: new ProductDescription(title: "Sample item Desc")))

        when:
        ProductDetailResponse response = ProductDetailResponseFactory.produce(1234, null, product)

        then:
        response!=null
        response.id == 1234
        response.currentPrice == null
        response.getName() == product.item.productDescription.title
    }

}
