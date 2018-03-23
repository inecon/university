package ua.com.foxminded.domain;

import java.io.Serializable;

public class Model implements Serializable {
    private static final Long serialVersionUID = 4211763759080896241L;

    private Long id;

    public Model(Long id) {
        this.id = id;
    }

    public Model() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
