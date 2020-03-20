package model.entity;

public class AccountCustomer {
    private Integer id;
    private String userName,passWord;
    private Customer customer;


    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
