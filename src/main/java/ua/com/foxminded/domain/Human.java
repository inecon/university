package ua.com.foxminded.domain;


import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Human implements Comparable <Human> {
    private String name;
    private String surName;
    private String gender;
    private Integer age;

    public Human() {}

    public Human(String name, String surName, String gender, Integer age) {
        this.name = name;
        this.surName = surName;
        this.gender = gender;
        this.age = age;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.age)
                .append(this.name)
                .toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (obj.getClass() != getClass()) {
            return false;
        }
        Human rhs = (Human) obj;
        return new EqualsBuilder()
                .append(name, rhs.getName())
                .append(surName, rhs.getSurName())
                .append(gender, rhs.getGender())
                .append(age, rhs.getAge())
                .isEquals();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.name + "\n");
        result.append(this.surName + "\n");
        result.append(this.gender + "\n");
        result.append(this.age + "\n");
        return result.toString();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public int compareTo(Human o) {
        return 0;
    }
}