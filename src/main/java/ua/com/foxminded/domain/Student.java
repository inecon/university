package ua.com.foxminded.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@SuppressWarnings("ALL")
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "students")
public class Student extends Human implements Serializable {
    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    public Student(Integer id, String name, String surName, String gender, Integer age, Group group) {
        super(id, name, surName, gender, age);
        this.group = group;
    }

    @Override
    public int compareTo(Human anotherStudent) {
        return (this.getSurName().compareTo(anotherStudent.getSurName()));
    }
}
