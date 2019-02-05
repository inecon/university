package ua.com.foxminded.domain;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@NoArgsConstructor
@Data
@MappedSuperclass
public class Human implements Comparable <Human> {
    @Id
    private Integer id;
    @Column
    private String name;
    @Column
    private String surName;
    @Column
    private String gender;
    @Column
    private Integer age;

    public Human(Integer id, String name, String surName, String gender, Integer age) {
        this.id = id;
        this.name = name;
        this.surName = surName;
        this.gender = gender;
        this.age = age;
    }

    @Override
    public int compareTo(Human o) {
        return 0;
    }
}
