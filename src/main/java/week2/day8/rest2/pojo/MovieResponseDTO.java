package week2.day8.rest2.pojo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 "page": 2,
  "per_page": 10,
  "total": 866,
  "total_pages": 87,
 */
public class MovieResponseDTO {

    private int page;
    private int per_page;
    private int total;
    private int total_pages;
    private List<MovieDTO> data;

    public List<MovieDTO> getData() {
        return data;
    }

    public void setData(List<MovieDTO> data) {
        this.data = data;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPer_page() {
        return per_page;
    }

    public void setPer_page(int per_page) {
        this.per_page = per_page;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    @Override
    public String toString() {
        return "MovieResponseDTO{" +
                "page=" + page +
                ", per_page=" + per_page +
                ", total=" + total +
                ", total_pages=" + total_pages +
                ", data=" + data +
                '}';
    }
}
