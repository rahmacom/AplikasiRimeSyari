package com.rahmacom.rimesyarifix.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Order {
    @SerializedName("id")
    private int id;

    @SerializedName("nomor")
    private String nomor;

    @SerializedName("pesan")
    private String pesan;

    @SerializedName("total")
    private int total;

    @SerializedName("jumlah")
    private int jumlah;

    @SerializedName("biaya_pengiriman")
    private int biayaPengiriman;

    @SerializedName("berat")
    private int berat;

    @SerializedName("expiry_date")
    private String expiryDate;

    @SerializedName("is_paid")
    private boolean isPaid;

    @SerializedName("discount_id")
    private int discountId;

    @SerializedName("status_id")
    private int statusId;

    @SerializedName("payment_method_id")
    private int paymentMethodId;

    @SerializedName("user_shipment_id")
    private int userShipmentId;

    @SerializedName("status")
    private Status status;

    @SerializedName("payment_method")
    private PaymentMethod paymentMethod;

    @SerializedName("user_shipment")
    private UserShipment userShipment;

    @SerializedName("products")
    private List<Product> products;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomor() {
        return nomor;
    }

    public void setNomor(String nomor) {
        this.nomor = nomor;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getBiayaPengiriman() {
        return biayaPengiriman;
    }

    public void setBiayaPengiriman(int biayaPengiriman) {
        this.biayaPengiriman = biayaPengiriman;
    }

    public int getBerat() {
        return berat;
    }

    public void setBerat(int berat) {
        this.berat = berat;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public int getDiscountId() {
        return discountId;
    }

    public void setDiscountId(int discountId) {
        this.discountId = discountId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(int paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public int getUserShipmentId() {
        return userShipmentId;
    }

    public void setUserShipmentId(int userShipmentId) {
        this.userShipmentId = userShipmentId;
    }

    public Status getStatus() {
        return status;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public UserShipment getUserShipment() {
        return userShipment;
    }

    public List<Product> getProducts() {
        return products;
    }
}