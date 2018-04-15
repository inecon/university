package ua.com.foxminded.domain;

import java.util.Set;
import java.util.TreeSet;

public class Teacher extends Human {

    private Set<String> subject = new TreeSet<String>();

    public Teacher() {

    }

    public Teacher(String name, String surName, String gender, Integer age) {
        super(name, surName, gender, age);
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Teacher) {
            Teacher t = (Teacher) o;
            return (this.getSurName().compareTo(((Teacher) o).getSurName()));
        } else
            return -1;

    }

    public Set<String> getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject.add(subject) ;
    }
}
