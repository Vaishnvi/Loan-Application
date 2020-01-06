package com.hackersdigital.loan.loandigital;

/**
 * Created by vaish on 01/02/19.
 */

public class CustAddress {
    String addrid;
    String flatno;
    String street ;
    String state ;
    String city ;
    String pincode ;

    public CustAddress(String id, String flatno, String street, String state, String city, String pincode)
    {
        this.addrid=id;
        this.flatno = flatno;
        this.street = street;
        this.state = state;
        this.city = city;
        this.pincode = pincode;
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

    public String  getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

}
