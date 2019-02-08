package ua.com.foxminded.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

@SuppressWarnings("ALL")
@NoArgsConstructor
@ToString(callSuper = true)
@Entity
@Table(name = "teachers")
public class Teacher extends Human {
    @Getter
    @Setter
    private String subject;

    public Teacher(Integer id, String name, String surName, String gender, Integer age, String subject) {
        super(id, name, surName, gender, age);
        this.subject = subject;
    }

    @Override
    public int compareTo(Human anotherTeacher) {
        return (this.getSurName().compareTo(anotherTeacher.getSurName()));
    }

}
