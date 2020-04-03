package com.myretail.productapi.services

import com.myretail.productapi.config.ProductRestClient
import com.myretail.productapi.dao.ProductDetailsRepository
import com.myretail.productapi.domain.Item
import com.myretail.productapi.domain.Price
import com.myretail.productapi.domain.Product
import com.myretail.productapi.domain.ProductDescription
import com.myretail.productapi.domain.ProductDetail
import com.myretail.productapi.domain.ProductDetailResponse
import spock.lang.Specification

class ProductDetailServiceSpec extends Specification{

    ProductDetailsRepository productDetailsRepository = Mock(ProductDetailsRepository)
    ProductRestClient productClient = Mock(ProductRestClient)

    ProductDetailsService productDetailService = new ProductDetailsServiceImpl(
            productDetailsRepository: productDetailsRepository,
            productRestClient: productClient
    )

    def "All product details available"(){

        given:
        ProductDetail productDetail = new ProductDetail(id: 1234, currentPrice: new Price(value: 10, currencyCode: "USD"))

        when:
        ProductDetailResponse result = productDetailService.getProductDetail(1234)

        then:
        1 * productDetailsRepository.findById(1234) >> new Optional<ProductDetail>(productDetail)
        1 * productClient.getProductById(1234) >> new Product(item: new Item(productDescription: new ProductDescription(title: "Sample item Desc")))
        0 * _

        result.id==1234
        result.name == "Sample item Desc"
        result.currentPrice == productDetail.currentPrice
    }

    def "Product price not available in database"(){
        when:
        ProductDetail result = productDetailService.getProductDetail(1234)

        then:
        1 * productDetailsRepository.findById(1234) >> new Optional<ProductDetail>()
        1 * productClient.getProductById(1234) >> new Product(item: new Item(productDescription: new ProductDescription(title: "Sample item Desc")))
        0 * _

        result.id==1234
        result.name == "Sample item Desc"
        result.currentPrice == null
    }

    def "Product detail not available in redsky"(){

        given:
        ProductDetail productDetail = new ProductDetail(id: 1234, currentPrice: new Price(value: 10, currencyCode: "USD"))

        when:
        ProductDetail result = productDetailService.getProductDetail(1234)

        then:
        1 * productDetailsRepository.findById(1234) >> new Optional<ProductDetail>(productDetail)
        1 * productClient.getProductById(1234) >> null
        0 * _

        result.id==1234
        result.currentPrice == productDetail.currentPrice
        result.name == null
    }

    def "getProductDetail throws exception"(){

        given:
        ProductDetail productDetail = new ProductDetail(id: 1234, currentPrice: new Price(value: 10, currencyCode: "USD"))

        when:
        ProductDetail result = productDetailService.getProductDetail(1234)

        then:
        1 * productClient.getProductById(1234) >> null
        1 * productDetailsRepository.findById(1234) >> {throw new Exception ("some exception")}

        0 * _

        thrown Exception
    }

    def "Update product price"(){
        given:
        ProductDetail productDetail = new ProductDetail(id: 1234, currentPrice: new Price(value: 10, currencyCode: "USD"))

        when:
        productDetailService.updateProductDetail(productDetail)

        then:
        1 * productDetailsRepository.save(productDetail)
        0 * _
    }

    def "Update product exception thrown"(){
        given:
        ProductDetail productDetail = new ProductDetail(id: 1234, currentPrice: new Price(value: 10, currencyCode: "USD"))

        when:
        productDetailService.updateProductDetail(productDetail)

        then:
        1 * productDetailsRepository.save(productDetail) >> {throw new Exception ("some exception")}
        0 * _

        thrown Exception
    }



}
