package com.rahmacom.rimesyarifix.data.local.entity;

import androidx.room.Embedded;
import androidx.room.Relation;

import com.rahmacom.rimesyarifix.data.entity.Product;
import com.rahmacom.rimesyarifix.data.entity.ProductCategory;

import java.util.List;

public class ProductsByCategory {
    @Embedded
    public ProductCategory productCategory;

    @Relation(
            parentColumn = "id",
            entityColumn = "productCategoryId"
    )
    public List<Product> products;
}
