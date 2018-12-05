package ua.com.foxminded.dao;

public class DaoException extends RuntimeException {
    public DaoException(Exception e) throws Exception {
        throw new Exception(e);
    }
}
