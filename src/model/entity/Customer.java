package model.entity;

public class Customer  {
    private Integer id;
    private String idCard,name,address,phone;

    public Customer() {
    }

    public Customer(Integer id, String idCard, String name, String address, String phone) {
        this.id = id;
        this.idCard = idCard;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public String getIdCard() {
        return idCard;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
