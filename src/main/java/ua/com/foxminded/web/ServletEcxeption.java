package ua.com.foxminded.web;

public class ServletEcxeption extends RuntimeException  {
    public ServletEcxeption(Exception e) throws Exception {
        throw new Exception(e);
    }
}
