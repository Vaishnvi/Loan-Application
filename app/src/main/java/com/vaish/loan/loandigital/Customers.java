package com.hackersdigital.loan.loandigital;

/**
 * Created by vaish on 29/01/19.
 */

public class Customers {

    String custId;
    String name ;
    String email ;
    String password ;
    String phone;
    String img;
    String flatno;
    String street ;
    String state ;
    String city ;
    String pincode ;


    public Customers(){

    }

    public Customers(String custId, String name, String email, String password, String phone, String img, String flatno, String street, String state, String city, String pincode) {
        this.custId = custId;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.img = img;
        this.flatno = flatno;
        this.street = street;
        this.state = state;
        this.city = city;
        this.pincode = pincode;
    }


    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getFlatno() {
        return flatno;
    }

    public void setFlatno(String flatno) {
        this.flatno = flatno;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }
}
