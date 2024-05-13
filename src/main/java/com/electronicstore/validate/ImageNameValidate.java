package com.electronicstore.validate;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;

@Target({ElementType.FIELD,ElementType.TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ImageNameValidator.class)
public @interface ImageNameValidate {

	//Represent image
    String message() default "Invalid image name...!!";

    //represent group of constraints
    Class<?>[] groups() default { };

    //Additional information about annotations
    Class<? extends Payload>[] payload() default { };

}
