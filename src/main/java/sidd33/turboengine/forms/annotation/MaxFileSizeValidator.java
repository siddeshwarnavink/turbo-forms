package sidd33.turboengine.forms.annotation;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MaxFileSizeValidator implements ConstraintValidator<MaxFileSize, Object> {
    private long max;
    @SuppressWarnings("unused")
    private String message;

    @Override
    public void initialize(MaxFileSize constraintAnnotation) {
        this.max = constraintAnnotation.max();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        if (value instanceof MultipartFile) {
            MultipartFile file = (MultipartFile) value;
            return file.isEmpty() || file.getSize() <= max;
        } else if (value instanceof MultipartFile[]) {
            MultipartFile[] files = (MultipartFile[]) value;
            for (MultipartFile file : files) {
                if (!file.isEmpty() && file.getSize() > max) {
                    return false;
                }
            }
            return true;
        } else {
            throw new IllegalArgumentException("Unsupported type for @MaxFileSize: " + value.getClass());
        }
    }
}
