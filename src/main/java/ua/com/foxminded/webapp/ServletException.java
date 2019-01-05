package ua.com.foxminded.webapp;

public class ServletException extends RuntimeException {
    public ServletException(Exception e) throws Exception {
        throw new Exception(e);
    }
}
