package com.rahmacom.rimesyarifix.data.entity;

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

    @SerializedName("kode_diskon")
    private String kodeDiskon;

    @SerializedName("biaya_pengiriman")
    private int biayaPengiriman;

    @SerializedName("berat")
    private int berat;

    @SerializedName("jumlah")
    private int jumlah;

    @SerializedName("status_id")
    private int statusId;

    @SerializedName("shipment_id")
    private int shipmentId;

    @SerializedName("shipment")
    private Shipment shipment;

    @SerializedName("status")
    private Status status;

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

    public String getKodeDiskon() {
        return kodeDiskon;
    }

    public void setKodeDiskon(String kodeDiskon) {
        this.kodeDiskon = kodeDiskon;
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

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(int shipmentId) {
        this.shipmentId = shipmentId;
    }

    public Shipment getShipment() {
        return shipment;
    }

    public Status getStatus() {
        return status;
    }

    public List<Product> getProducts() {
        return products;
    }

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", nomor='" + nomor + '\'' + ", pesan='" + pesan + '\'' + ", total=" + total + ", kodeDiskon='" + kodeDiskon + '\'' + ", biayaPengiriman=" + biayaPengiriman + ", berat=" + berat + ", jumlah=" + jumlah + ", statusId=" + statusId + ", shipmentId=" + shipmentId + ", shipment=" + shipment + ", status=" + status + ", products=" + products + '}';
    }
}