package com.myretail.productapi.domain;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * This is a constraint used to validate ProductDetail bean.
 *
 * @author Selvaraj Karuppusamy
 */
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ProductDetailConstraintValidator.class)
@Documented
public @interface ProductDetailConstraint {
    String message() default "Invalid product detail";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
