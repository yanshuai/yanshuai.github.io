package model;

import javax.validation.constraints.NotNull;

public class App {

    private long id;

    @NotNull
    private String name;
    
    @NotNull
    private String description;

    public App() {
    }

    public App(long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
