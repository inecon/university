package ua.com.foxminded.domain;

import java.util.Set;
import java.util.TreeSet;

public class Teacher extends Human {

    private Set<String> subject = new TreeSet<String>();

    public Teacher() {}

    public Teacher(String name, String surName, String gender, Integer age) {
        super(name, surName, gender, age);
    }

    @Override
    public int compareTo(Object another) {
        if (another instanceof Teacher) {
            Teacher anotherTeacher = (Teacher) another;
            return (this.getSurName().compareTo(anotherTeacher.getSurName()));
        } else {
            return -1;
        }
    }

    @Override
    public String toString(){
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
