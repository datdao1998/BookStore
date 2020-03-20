package model.entity;

public class AccountStaff {
    private Integer id;
    private String userName,passWord;
    private Staff staff;

    public Integer getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public Staff getStaff() {
        return staff;
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

    public void setStaff(Staff staff) {
        this.staff = staff;
    }
}
