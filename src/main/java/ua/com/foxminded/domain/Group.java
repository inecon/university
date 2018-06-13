package ua.com.foxminded.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Group implements Comparable<Group> {
    private Integer id;
    private String title;
    private String description;

    public Group() {}

    public Group(Integer id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder()
                .append(this.title)
                .append(this.description)
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
        Group rhs = (Group) obj;
        return new EqualsBuilder()
                .append(id,rhs.getId())
                .append(title, rhs.getTitle())
                .append(description, rhs.getDescription())
                .isEquals();
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(this.id + " ");
        result.append(this.title + "\n");
        result.append(" - \"" + this.description + "\"" + "\n");
        return result.toString();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(Group anotherGroup) {
        return (this.id.compareTo(anotherGroup.id));
    }
}
