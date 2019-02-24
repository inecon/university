package ua.com.foxminded.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
@Data
public class ApiError {

    private Integer errorCode;
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ApiError() {
        super();
    }

    public ApiError(Integer errorCode, HttpStatus status, String message, List<String> errors) {
        super();
        this.errorCode = errorCode;
        this.status = status;
        this.message = message;
        this.errors = errors;
    }

    public ApiError(Integer errorCode, HttpStatus status, String message, String error) {
        super();
        this.errorCode = errorCode;
        this.status = status;
        this.message = message;
        errors = Arrays.asList(error);
    }
}