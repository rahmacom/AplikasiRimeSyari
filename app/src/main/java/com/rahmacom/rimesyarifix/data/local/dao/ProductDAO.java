package com.rahmacom.rimesyarifix.data.local.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.rahmacom.rimesyarifix.data.entity.Product;

import java.util.List;

@Dao
public interface ProductDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Product produk);

    @Query("SELECT * FROM products")
    LiveData<List<Product>> getProducts();

}
