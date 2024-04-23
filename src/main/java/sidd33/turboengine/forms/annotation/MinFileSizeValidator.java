package sidd33.turboengine.forms.annotation;

import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class MinFileSizeValidator implements ConstraintValidator<MinFileSize, Object> {
    private long min;
    @SuppressWarnings("unused")
	private String message;

    @Override
    public void initialize(MinFileSize constraintAnnotation) {
        this.min = constraintAnnotation.min();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        if (value instanceof MultipartFile) {
            MultipartFile file = (MultipartFile) value;
            return file.getSize() >= min;
        } else if (value instanceof MultipartFile[]) {
            MultipartFile[] files = (MultipartFile[]) value;
            for (MultipartFile file : files) {
                if (file.isEmpty() || file.getSize() < min) {
                    return false;
                }
            }
            return true;
        } else {
            throw new IllegalArgumentException("Unsupported type for @MinFileSize: " + value.getClass());
        }
    }
}
