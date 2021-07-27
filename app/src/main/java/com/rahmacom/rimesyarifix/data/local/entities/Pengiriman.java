package com.rahmacom.rimesyarifix.data.local.entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Pengiriman {

    @PrimaryKey
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
