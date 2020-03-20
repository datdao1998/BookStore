package model.entity;

public class Book {

    private Integer id;

    private String name;

    private String author;

    private String publisher;

    private Double price;

    private Integer available;

    private String category;

    public Book(Integer id, String name, String author, String publisher,String category, Double price, Integer available) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.price = price;
        this.available = available;
        this.category = category;
    }

    public Book() {
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getAvailable() {
        return available;
    }

    public Integer getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public String getPublisher() {
        return publisher;
    }

    public Double getPrice() {
        return price;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setAvailable(Integer available) {
        this.available = available;
    }
}
