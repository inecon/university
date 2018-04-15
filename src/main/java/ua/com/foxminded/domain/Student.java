package ua.com.foxminded.domain;

public class Student extends Human {
    private Group group;

    public Student() {

    }

    public Student(String name, String surName, String gender, Integer age) {
        super(name, surName, gender, age);
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof Student) {
            Student s = (Student) o;
            return (this.getSurName().compareTo(((Student) o).getSurName()));
        } else
            return -1;

    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
