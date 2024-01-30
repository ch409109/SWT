package model;

import java.sql.Date;

public class Category {

    private int categoryId;
    private String categoryName;
    private int createdBy;
    private Date createdOn;
    private int modifiedBy;
    private Date modifiedOn;

    public Category() {
    }

    public Category(int categoryId, String categoryName, int createdBy, Date createdOn, int modifiedBy, Date modifiedOn) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.modifiedBy = modifiedBy;
        this.modifiedOn = modifiedOn;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
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

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
