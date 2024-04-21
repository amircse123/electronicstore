package com.lcwd.electronic.store.electronicstore.validate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.annotation.Annotation;

public class ImageNameValidator implements ConstraintValidator<ImageNameValid, String> {

    Logger logger=LoggerFactory.getLogger(ImageNameValidator.class);

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        logger.info("message from isValid : {}",value);

        if (value.isEmpty()) {

            return false;
        } else {
            return true;
        }


    }
}
