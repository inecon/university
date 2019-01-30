package ua.com.foxminded.domain;


import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
public class Human implements Comparable <Human> {
    private Integer id;
    private String name;
    private String surName;
    private String gender;
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
