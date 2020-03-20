package model.entity;

public class Staff {
    private Integer id;
    private String name,staffCode,phone;

    public Staff(Integer id, String name, String staffCode, String phone) {
        this.id = id;
        this.name = name;
        this.staffCode = staffCode;
        this.phone = phone;
    }

    public Staff() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
