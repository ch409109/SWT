package model;

import java.sql.Date;

public class Product {

    private int productId;
    private Category category;
    private String productName;
    private String image;
    private String description;
    private double price;
    private int quantity;
    private String status;
    private double discount;
    private int createdBy;
    private Date createdOn;
    private int modifiedBy;
    private Date modifiedOn;

    public Product() {
    }

    public Product(int productId, Category category, String productName, String image, String description, double price, int quantity, String status, double discount, int createdBy, Date createdOn, int modifiedBy, Date modifiedOn) {
        this.productId = productId;
        this.category = category;
        this.productName = productName;
        this.image = image;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.status = status;
        this.discount = discount;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.modifiedBy = modifiedBy;
        this.modifiedOn = modifiedOn;
    }

    public int getProductId() {
        return productId;
    }

    public Category getCategory() {
        return category;
    }

    public String getProductName() {
        return productName;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getStatus() {
        return status;
    }

    public double getDiscount() {
        return discount;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public int getModifiedBy() {
        return modifiedBy;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public void setModifiedBy(int modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

}
