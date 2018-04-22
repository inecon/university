package ua.com.foxminded.domain;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Group implements Comparable {

    private String title;
    private String description;

    public Group(String title, String description) {
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
                .append(title, rhs.getTitle())
                .append(description, rhs.getDescription())
                .isEquals();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
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

    public Group() {}

    public int compareTo(Object o) {
        if (o instanceof Group) {
            Group g = (Group) o;
            return (this.title.compareTo(((Group) o).title));
        } else
            return -1;
    }
}
