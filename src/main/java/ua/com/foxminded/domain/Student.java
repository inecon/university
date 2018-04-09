package ua.com.foxminded.domain;

public class Student extends Human {
    private Group group;

    public Student() {

    }

    public Student(String name, String surName, String gender, Integer age) {
        super(name, surName, gender, age);
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
