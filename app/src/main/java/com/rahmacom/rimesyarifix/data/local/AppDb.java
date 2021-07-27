package com.rahmacom.rimesyarifix.data.local;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.rahmacom.rimesyarifix.data.local.dao.KeranjangDAO;
import com.rahmacom.rimesyarifix.data.local.dao.OrderDAO;
import com.rahmacom.rimesyarifix.data.local.dao.PengirimanDAO;
import com.rahmacom.rimesyarifix.data.local.dao.ProdukDAO;
import com.rahmacom.rimesyarifix.data.local.dao.TransaksiDAO;
import com.rahmacom.rimesyarifix.data.local.entities.Keranjang;
import com.rahmacom.rimesyarifix.data.local.entities.Order;
import com.rahmacom.rimesyarifix.data.local.entities.Pengiriman;
import com.rahmacom.rimesyarifix.data.local.entities.Produk;
import com.rahmacom.rimesyarifix.data.local.entities.Transaksi;

@Database(
        entities = {
                Keranjang.class,
                Order.class,
                Pengiriman.class,
                Produk.class,
                Transaksi.class
        },
        version = 1,
        exportSchema = false
)
public abstract class AppDb extends RoomDatabase {

    public abstract KeranjangDAO keranjangDAO();

    public abstract OrderDAO orderDAO();

    public abstract PengirimanDAO pengirimanDAO();

    public abstract ProdukDAO produkDAO();

    public abstract TransaksiDAO transaksiDAO();

}
