package ua.com.foxminded.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@ToString(callSuper = true)
public class Student extends Human {
    @Setter
    @Getter
    private Group group;

    public Student(Integer id, String name, String surName, String gender, Integer age) {
        super(id, name, surName, gender, age);
    }

    @Override
    public int compareTo(Human anotherStudent) {
        return (this.getSurName().compareTo(anotherStudent.getSurName()));
    }
}
