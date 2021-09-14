package com.rahmacom.rimesyarifix.data.model;

import com.google.gson.annotations.SerializedName;

public class UserShipment {

    @SerializedName("id")
    private int id;

    @SerializedName("alamat")
    private String alamat;

    @SerializedName("kode_pos")
    private String kodePos;

    @SerializedName("catatan")
    private String catatan;

    @SerializedName("is_default")
    private boolean isDefault;

    @SerializedName("village_id")
    private long villageId;

    @SerializedName("desa_kel")
    private String desaKel;

    @SerializedName("village")
    private Village village;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKodePos() {
        return kodePos;
    }

    public void setKodePos(String kodePos) {
        this.kodePos = kodePos;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    public boolean isDefault() {
        return isDefault;
    }

    public void setDefault(boolean aDefault) {
        isDefault = aDefault;
    }

    public long getVillageId() {
        return villageId;
    }

    public void setVillageId(long villageId) {
        this.villageId = villageId;
    }

    public Village getVillage() {
        return village;
    }

    public void setVillage(Village village) {
        this.village = village;
    }

    public String getDesaKel() {
        return desaKel;
    }

    public void setDesaKel(String desaKel) {
        this.desaKel = desaKel;
    }
}