package ua.com.foxminded.domain;

public class Student extends Human {
    private Group group;

    public Student() {}

    public Student(String name, String surName, String gender, Integer age) {
        super(name, surName, gender, age);
    }

    @Override
    public int compareTo(Object another) {
        if (another instanceof Student) {
            Student anotherStudent = (Student) another;
            return (this.getSurName().compareTo(anotherStudent.getSurName()));
        } else {
            return -1;
        }
    }

    @Override
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append(super.toString());
        result.append(this.group + "\n");
        return result.toString();
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}
