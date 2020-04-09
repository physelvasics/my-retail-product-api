package com.myretail.productapi.domain;

import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * This is the implementation of the constraint validator of ProductDetail.
 *
 * @author Selvaraj Karuppusamy
 */
public class ProductDetailConstraintValidator implements ConstraintValidator<ProductDetailConstraint, ProductDetail> {
    @Override
    public void initialize(ProductDetailConstraint constraintAnnotation) {

    }

    /**
     * This method does the validation operation and returns boolean value based of the validation result.
     * @param productDetail which has to validated.
     * @param constraintValidatorContext current context
     * @return boolean value represents validation status.
     */
    @Override
    public boolean isValid(ProductDetail productDetail, ConstraintValidatorContext constraintValidatorContext) {

        return productDetail != null &&
                productDetail.getCurrentPrice() != null &&
                !StringUtils.isEmpty(productDetail.getCurrentPrice().getCurrencyCode()) &&
                productDetail.getCurrentPrice().getValue() != null;
    }
}
