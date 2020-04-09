package com.myretail.productapi.controller

import com.myretail.productapi.domain.Price
import com.myretail.productapi.domain.ProductDetail
import com.myretail.productapi.domain.ProductDetailResponse
import com.myretail.productapi.services.ProductDetailsService
import spock.lang.Specification

import javax.servlet.http.HttpServletResponse

class ProductControllerSpec extends Specification{

    ProductDetailsService productDetailsService = Mock(ProductDetailsService)

    ProductDetailsController productDetailsController = new ProductDetailsController(productDetailsService: productDetailsService)
    HttpServletResponse httpServletResponse = Mock(HttpServletResponse)

    def "Valid get request"(){
        given:
        ProductDetailResponse expected = new ProductDetailResponse(id: 1234)

        when:
        ProductDetailResponse actual = productDetailsController.getProductDetails(1234, httpServletResponse)

        then:
        1 * productDetailsService.getProductDetail(1234) >> expected
        0 * _

        expected == actual
    }

    def "Valid get request but no data found"(){
        given:

        when:
        productDetailsController.getProductDetails(1234, httpServletResponse)

        then:
        1 * productDetailsService.getProductDetail(1234) >> null
        1 * httpServletResponse.setStatus(HttpServletResponse.SC_NOT_FOUND)
        0 * _
    }

    def "Valid put request"(){
        given:
        ProductDetail productDetail = new ProductDetail(id: 1234, currentPrice: new Price(value: 12.5, currencyCode: "USD"))

        when:
        ProductDetail actual = productDetailsController.putProductDetails(1234,productDetail, httpServletResponse)

        then:
        1 * productDetailsService.updateProductDetail(1234, productDetail) >> productDetail
        1 * httpServletResponse.setStatus(201)
        0 * _
        productDetail == actual
    }

    def "Valid put request no content"(){
        given:
        ProductDetail productDetail = new ProductDetail(id: 1234, currentPrice: new Price(value: 12.5, currencyCode: "USD"))

        when:
        ProductDetail actual = productDetailsController.putProductDetails(1234,productDetail, httpServletResponse)

        then:
        1 * productDetailsService.updateProductDetail(1234, productDetail) >> null
        1 * httpServletResponse.setStatus(204)
        0 * _
        null == actual
    }

    def "Valid post request"(){
        given:
        ProductDetail productDetail = new ProductDetail(id: 1234, currentPrice: new Price(value: 12.5, currencyCode: "USD"))

        when:
        Integer actual = productDetailsController.postProductDetails(productDetail, httpServletResponse)

        then:
        1 * productDetailsService.insertProductDetail(productDetail) >> 1234
        1 * httpServletResponse.setStatus(201)
        0 * _
        1234 == actual
    }
}
