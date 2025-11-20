package week2.day8.rest2.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MovieDTO {
    @JsonProperty("Year")
    private int year;
    @JsonProperty("Title")
    private String title;
    private String imdbID;

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    @Override
    public String toString() {
        return "MovieDTO{" +
                "year=" + year +
                ", title='" + title + '\'' +
                ", imdbID='" + imdbID + '\'' +
                '}';
    }
}
