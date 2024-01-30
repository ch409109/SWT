package model;

import java.sql.Date;

public class Order {

    private int orderId;
    private Customer customer;
    private String orderStatus;
    private Date orderDate;
    private String paymentMethod;
    private String shippingAddress;
    private double freight;
    private String transactionStatus;

    public Order() {
    }

    public Order(int orderId, Customer customer, String orderStatus, Date orderDate, String paymentMethod, String shippingAddress, double freight, String transactionStatus) {
        this.orderId = orderId;
        this.customer = customer;
        this.orderStatus = orderStatus;
        this.orderDate = orderDate;
        this.paymentMethod = paymentMethod;
        this.shippingAddress = shippingAddress;
        this.freight = freight;
        this.transactionStatus = transactionStatus;
    }

    public int getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public double getFreight() {
        return freight;
    }

    public String getTransactionStatus() {
        return transactionStatus;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public void setFreight(double freight) {
        this.freight = freight;
    }

    public void setTransactionStatus(String transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

}
