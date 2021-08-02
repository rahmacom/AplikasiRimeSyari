package com.rahmacom.rimesyarifix.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.rahmacom.rimesyarifix.data.local.entities.Produk;

import java.util.List;

@Dao
public interface ProdukDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Produk produk);

    @Query("SELECT * FROM produk")
    LiveData<List<Produk>> getProduk();

}
