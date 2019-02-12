package ua.com.foxminded.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@NoArgsConstructor
@Data
@Entity
@Table(name = "groups")
public class Group implements Comparable<Group>, Serializable {
    @Id
    private Integer id;
    @Column
    private String title;
    @Column
    private String description;

    public Group(Integer id, String title, String description) {
        this.id = id;
        this.title = title;
        this.description = description;
    }

    @Override
    public int compareTo(Group anotherGroup) {
        return (this.id.compareTo(anotherGroup.id));
    }
}
