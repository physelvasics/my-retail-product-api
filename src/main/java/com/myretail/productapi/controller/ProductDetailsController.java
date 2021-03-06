package com.myretail.productapi.controller;

import com.myretail.productapi.domain.ProductDetail;
import com.myretail.productapi.domain.ProductDetailResponse;
import com.myretail.productapi.services.ProductDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/v1")
public class ProductDetailsController {
    private static final Logger log = LoggerFactory.getLogger(ProductDetailsController.class);

    @Autowired
    private ProductDetailsService productDetailsService;

    /**
     * This GET endpoint will serve product detail for given id.
     *
     * <b>Note: </b>The response may have one of following.
     * <p>
     *    <ul>
     *       <li>ProductDetail data with status code 200 in case of successful retrieval.</li>
     *       <li>null value with status code of 500 in case of any exception.</li>
     *       <li>null value with status code of 400 in case of invalid input.</li>
     *    </ul>
     * </p>
     *
     * @param id       is a product id for which the product details has to be retrieved.
     * @param response is used to send http status codes based on data retrieval operation status.
     * @return ProductDetail for given id.
     */
    @GetMapping(path = "/products/{id}", produces = "application/json")
    ProductDetailResponse getProductDetails(@PathVariable Integer id, HttpServletResponse response) {
        log.info("Incoming get request, id={}", id);

        ProductDetailResponse productDetailResponse = productDetailsService.getProductDetail(id);
        if (productDetailResponse == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        }

        return productDetailResponse;
    }


    @PostMapping(path = "/products", consumes = "application/json")
    Integer postProductDetails(
                             @Valid @RequestBody ProductDetail productDetail,
                             HttpServletResponse response) {
        log.info("Incoming post request, id={}", productDetail.getId());

        productDetailsService.insertProductDetail(productDetail);
        response.setStatus(HttpServletResponse.SC_CREATED);
        return productDetail.getId();
    }


    @PutMapping(path = "/products/{id}", consumes = "application/json")
    ProductDetail putProductDetails(@PathVariable Integer id,
                             @Valid @RequestBody ProductDetail productDetail,
                             HttpServletResponse response) {
        log.info("Incoming put request, id={}", id);

        ProductDetail responseEntity = productDetailsService.updateProductDetail(id, productDetail);
        if(responseEntity!=null){
            response.setStatus(HttpServletResponse.SC_CREATED);
        }else {
            response.setStatus(HttpServletResponse.SC_NO_CONTENT);
        }
        return responseEntity;
    }
}
