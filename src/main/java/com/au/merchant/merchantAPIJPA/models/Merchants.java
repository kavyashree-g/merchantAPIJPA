package com.au.merchant.merchantAPIJPA.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "merchants")
public class Merchants {

    public Merchants() {
    }

    @Id
    @Column(name = "merchant_id", nullable = false)
    private int merchantId;
    @Column(name = "merchant_name", nullable = false)
    private String merchantName;
    @Column(name = "email_address", nullable = false)
    private String emailAddress;


    public Merchants(int merchantId, String merchantName, String emailAddress) {
        this.merchantId = merchantId;
        this.merchantName = merchantName;
        this.emailAddress = emailAddress;
    }


    public int getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(int merchantId) {
        this.merchantId = merchantId;
    }

    public String getMerchantName() {
        return merchantName;
    }

    public void setMerchantName(String merchantName) {
        this.merchantName = merchantName;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

}
