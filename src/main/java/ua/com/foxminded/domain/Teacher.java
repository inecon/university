package ua.com.foxminded.domain;

import java.util.Set;

public class Teacher extends Human {

    private Set<String> subject;

    public Teacher() {

    }

    public Teacher(String name, String surName, String gender, Integer age) {
        super(name, surName, gender, age);
    }

    public Set<String> getSubject() {
        return subject;
    }

    public void setSubject(Set<String> subject) {
        this.subject = subject;
    }
}
