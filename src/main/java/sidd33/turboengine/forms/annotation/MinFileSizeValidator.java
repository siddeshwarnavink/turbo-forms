package sidd33.turboengine.forms.annotation;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MinFileSizeValidator implements ConstraintValidator<MinFileSize, MultipartFile> {
    private long min;
    private String message;

    @Override
    public void initialize(MinFileSize constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null || file.isEmpty()) {
            return false; 
        }

        return file.getSize() >= min;
    }

}
