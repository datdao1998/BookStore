package model.entity;

public class OrderBill {

    public static class STATUS {
        public static final String WAIT_CONFIRM = "wait confirm";
        public static final String CONFIRMED = "confirmed";
        public static final String SHIPPING = "shipping";
        public static final String DONE = "done";
    }

    private Integer id;

    private Customer customer;

    private String status;

    private String staffName;

    private String staffCode;

    public Customer getCustomer() {
        return customer;
    }

    public Integer getId() {
        return id;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public String getStaffName() {
        return staffName;
    }

    public String getStatus() {
        return status;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
