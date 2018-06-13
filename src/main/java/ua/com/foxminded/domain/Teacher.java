package ua.com.foxminded.domain;

import java.util.Set;
import java.util.TreeSet;

public class Teacher extends Human {

    private Set<String> subject = new TreeSet<String>();

    public Teacher() {}

    public Teacher(Integer id, String name, String surName, String gender, Integer age) {
        super(id, name, surName, gender, age);
    }

    @Override
    public int compareTo(Human anotherTeacher) {
        return (this.getSurName().compareTo(anotherTeacher.getSurName()));
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(super.toString());
        result.append("subject - " + this.subject + "\n");
        return result.toString();
    }

    public Set<String> getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject.add(subject);
    }
}
