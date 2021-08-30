package com.rahmacom.rimesyarifix.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "shipments")
public class Shipment {

    @PrimaryKey
    @SerializedName("id")
    private int id;

    @ColumnInfo(name = "alamat")
    @SerializedName("alamat")
    private String alamat;

    @ColumnInfo(name = "village_id")
    @SerializedName("village_id")
    private String villageId;

    @ColumnInfo(name = "kode_pos")
    @SerializedName("kode_pos")
    private String kodePos;

    @ColumnInfo(name = "catatan")
    @SerializedName("catatan")
    private String catatan;

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

    public String getVillageId() {
        return villageId;
    }

    public void setVillageId(String villageId) {
        this.villageId = villageId;
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

    @Override
    public String toString() {
        return "Shipment{" + "id=" + id + ", alamat='" + alamat + '\'' + ", villageId='" + villageId + '\'' + ", kodePos='" + kodePos + '\'' + ", catatan='" + catatan + '\'' + '}';
    }
}