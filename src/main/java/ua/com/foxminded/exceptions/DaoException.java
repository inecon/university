package ua.com.foxminded.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
public class DaoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DaoException(String msg) {
        super(msg);
    }

    public DaoException(Exception e) {
    }

}