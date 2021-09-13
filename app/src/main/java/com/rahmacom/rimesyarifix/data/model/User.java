package com.rahmacom.rimesyarifix.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class User {
    @SerializedName("nik")
    private String nik;

    @SerializedName("nama_lengkap")
    private String namaLengkap;

    @SerializedName("email")
    private String email;

    @SerializedName("jenis_kelamin")
    private String jenisKelamin;

    @SerializedName("jk")
    private char jk;

    @SerializedName("alamat")
    private String alamat;

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
    private Image avatar;

    @SerializedName("user_shipments")
    private List<UserShipment> userShipments;

    @SerializedName("roles")
    private List<String> roles;

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNamaLengkap() {
        return namaLengkap;
    }

    public void setNamaLengkap(String namaLengkap) {
        this.namaLengkap = namaLengkap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getJenisKelamin() {
        return jenisKelamin;
    }

    public void setJenisKelamin(String jenisKelamin) {
        this.jenisKelamin = jenisKelamin;
    }

    public char getJk() {
        return jk;
    }

    public void setJk(char jk) {
        this.jk = jk;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTempatLahir() {
        return tempatLahir;
    }

    public void setTempatLahir(String tempatLahir) {
        this.tempatLahir = tempatLahir;
    }

    public String getTglLahir() {
        return tglLahir;
    }

    public void setTglLahir(String tglLahir) {
        this.tglLahir = tglLahir;
    }

    public String getNoHp() {
        return noHp;
    }

    public void setNoHp(String noHp) {
        this.noHp = noHp;
    }

    public String getNoWa() {
        return noWa;
    }

    public void setNoWa(String noWa) {
        this.noWa = noWa;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Image getAvatar() {
        return avatar;
    }

    public void setAvatar(Image avatar) {
        this.avatar = avatar;
    }

    public List<UserShipment> getUserShipments() {
        return userShipments;
    }

    public void setUserShipments(List<UserShipment> userShipments) {
        this.userShipments = userShipments;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}