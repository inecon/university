package ua.com.foxminded.domain;

public class Group extends Model {
    private static final long serialVersionUID = 5093824429299325045L;

    private String title;
    private String description;

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

    public Group() {

    }
}
