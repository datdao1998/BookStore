package model.entity;


public class OrderedItem {
    private Integer id;
    private Book book;
    private OrderBill orderBill;
    private Integer quantity;

    public OrderedItem() {
    }

    public OrderedItem(Integer id, Book book, OrderBill orderBill, Integer quantity) {
        this.id = id;
        this.book = book;
        this.orderBill = orderBill;
        this.quantity = quantity;
    }

    public Integer getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public OrderBill getOrderBill() {
        return orderBill;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setOrderBill(OrderBill orderBill) {
        this.orderBill = orderBill;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
