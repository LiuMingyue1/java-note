package week2.day8.rest1.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Student {
    private String id;
    @JsonProperty("stu_name")
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
