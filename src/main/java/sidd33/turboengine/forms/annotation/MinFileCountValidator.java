package sidd33.turboengine.forms.annotation;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MinFileCountValidator implements ConstraintValidator<MinFileCount, MultipartFile[]> {
    private long length;
    @SuppressWarnings("unused")
    private String message;
    
    @Override
    public void initialize(MinFileCount constraintAnnotation) {
        this.message = constraintAnnotation.message();
        this.length = constraintAnnotation.length();
    }

    @Override
    public boolean isValid(MultipartFile[] value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        if(value.length < length) {
            return false;
        }

        return true;
    }
}
