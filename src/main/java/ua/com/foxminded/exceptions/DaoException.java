package ua.com.foxminded.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
@NoArgsConstructor
public class DaoException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public DaoException(SQLException e) {
    }

    public DaoException(String msg) {
        super(msg);
    }

    public DaoException(Exception e) {
    }

}