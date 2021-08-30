package com.rahmacom.rimesyarifix.data.entity;

import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.util.List;

public class User {

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("jenis_kelamin")
    private String jenisKelamin;

    @SerializedName("jk")
    private Character jk;

    @SerializedName("tempat_lahir")
    private String tempatLahir;

    @SerializedName("tgl_lahir")
    private String tglLahir;

    @SerializedName("no_hp")
    private String noHp;

    @SerializedName("no_wa")
    private String noWa;

    @SerializedName("created_at")
    private String createdAt;

    @SerializedName("avatar")
    private File avatar;

    @SerializedName("shipments")
    private List<Shipment> shipments;

    @SerializedName("shipment")
    private Shipment shipment;

    @SerializedName("roles")
    private List<String> roles;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public String getNoHp() {
        return noHp;
    }

    public String getNoWa() {
        return noWa;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public File getAvatar() {
        return avatar;
    }

    public List<Shipment> getShipments() {
        return shipments;
    }

    public List<String> getRoles() {
        return roles;
    }

    public Character getJk() {
        return jk;
    }

    public Shipment getShipment() {
        return shipment;
    }
}