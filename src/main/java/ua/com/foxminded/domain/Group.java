package ua.com.foxminded.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@Data
public class Group implements Comparable<Group> {
    private Integer id;
    private String title;
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
