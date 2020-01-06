package com.hackersdigital.loan.dailycollector;

/**
 * Created by vaish on 03/02/19.
 */

public class LendMoneyInfo {

    String custId;
    String lendInfoID;
    Long accNo;
    Long totalAmount;
    Long number_of_days;
    Double rate_of_interest;
    Long dailyAmount;
    Long interest_amount_to_be_collected;
    Long balance;
    Long amount_collected;
    String status;
    Long due;
    String date;

    public LendMoneyInfo(){

    }

    public LendMoneyInfo(String custId, String lendInfoID, Long accNo, Long totalAmount, Long number_of_days, Double rate_of_interest, Long dailyAmount, Long interest_amount_to_be_collected, Long balance, Long amount_collected, String status, Long due, String date) {
        this.custId = custId;
        this.lendInfoID = lendInfoID;
        this.accNo = accNo;
        this.totalAmount = totalAmount;
        this.number_of_days = number_of_days;
        this.rate_of_interest = rate_of_interest;
        this.dailyAmount = dailyAmount;
        this.interest_amount_to_be_collected = interest_amount_to_be_collected;
        this.balance = balance;
        this.amount_collected = amount_collected;
        this.status = status;
        this.due = due;
        this.date = date;
    }

    public Long getAccNo() {
        return accNo;
    }

    public void setAccNo(Long accNo) {
        this.accNo = accNo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }


    public Long getAmount_collected() {
        return amount_collected;
    }

    public void setAmount_collected(Long amount_collected) {
        this.amount_collected = amount_collected;
    }

    public String getStatus() {
        return status;

    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getDue() {
        return due;
    }

    public void setDue(Long due) {
        this.due = due;
    }


    public String getLendInfoID() {
        return lendInfoID;
    }

    public Long getInterest_amount_to_be_collected() {
        return interest_amount_to_be_collected;
    }

    public void setInterest_amount_to_be_collected(Long interest_amount_to_be_collected) {
        this.interest_amount_to_be_collected = interest_amount_to_be_collected;
    }

    public void setLendInfoID(String lendInfoID) {
        this.lendInfoID = lendInfoID;
    }

    public Long getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getNumber_of_days() {
        return number_of_days;
    }

    public void setNumber_of_days(Long number_of_days) {
        this.number_of_days = number_of_days;
    }

    public Double getRate_of_interest() {
        return rate_of_interest;
    }

    public void setRate_of_interest(Double rate_of_interest) {
        this.rate_of_interest = rate_of_interest;
    }

    public Long getDailyAmount() {
        return dailyAmount;
    }

    public void setDailyAmount(Long dailyAmount) {
        this.dailyAmount = dailyAmount;
    }
}
