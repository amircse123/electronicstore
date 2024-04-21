package com.lcwd.electronic.store.electronicstore.validate;


//Making custom annotation

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.TYPE_PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ImageNameValidator.class)
public @interface ImageNameValid {

    //Represent image
    String message() default "Invalid image name...!!";

    //represent group of constraints
    Class<?>[] groups() default { };

    //Additional information about annotations
    Class<? extends Payload>[] payload() default { };


}
