package com.rahmacom.rimesyarifix.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.rahmacom.rimesyarifix.data.entity.Product;
import com.rahmacom.rimesyarifix.data.local.dao.ProductDAO;

@Database(entities = {
        Product.class
}, version = 1, exportSchema = false)
public abstract class AppDb extends RoomDatabase {

    public abstract ProductDAO productDAO();

}
