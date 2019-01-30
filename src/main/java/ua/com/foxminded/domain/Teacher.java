package ua.com.foxminded.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;

@NoArgsConstructor
@ToString(callSuper = true)
public class Teacher extends Human {

    @Getter
    private ArrayList<String> subject = new ArrayList<String>();

    public Teacher(Integer id, String name, String surName, String gender, Integer age) {
        super(id, name, surName, gender, age);
    }

    @Override
    public int compareTo(Human anotherTeacher) {
        return (this.getSurName().compareTo(anotherTeacher.getSurName()));
    }

    public void setSubject(String subject) {
        this.subject.add(subject);
    }
}
