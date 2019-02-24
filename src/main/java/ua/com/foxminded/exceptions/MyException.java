package ua.com.foxminded.exceptions;

import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component
@NoArgsConstructor
public class MyException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public MyException(SQLException e) {
    }

    public MyException(String msg) {
        super(msg);
    }

    public MyException(Exception e) {
    }

}