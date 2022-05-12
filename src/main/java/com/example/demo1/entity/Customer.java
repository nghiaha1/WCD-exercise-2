package com.example.demo1.entity;

import java.time.LocalDateTime;

public class Customer {
    private int ID;
    private String name;
    private String phone;
    private String img;
    private LocalDateTime dob;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private int status;

    public Customer(int ID, String name, String phone, String img, LocalDateTime dob, LocalDateTime createAt, LocalDateTime updateAt, int status) {
        this.ID = ID;
        this.name = name;
        this.phone = phone;
        this.img = img;
        this.dob = dob;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.status = status;
    }

    public Customer( String name, String phone, String img, LocalDateTime dob) {
        this.name = name;
        this.phone = phone;
        this.img = img;
        this.dob = dob;
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
        this.status = 1;
    }

    public Customer(){
    }



    public String toString(){
        return "Customer{"+
                "ID='" + ID + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", dob=" + dob +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", status=" + status +
                '}';
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String Name) {
        this.name = Name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public LocalDateTime getDob() {
        return dob;
    }

    public void setDob(LocalDateTime dob) {
        this.dob = dob;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
