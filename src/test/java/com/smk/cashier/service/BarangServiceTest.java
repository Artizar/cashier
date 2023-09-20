package com.smk.cashier.service;

import com.smk.cashier.model.Barang;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class BarangServiceTest {

    @Test
    @Order(2)
    void getBarangList() {
        List<Barang> bList = BarangService.getInstance().getBarangList();
        assertEquals(bList.size(), 2);
    }

    @Test
    @Order(1)
    void addBarang() {
        Barang laptop = new Barang();
        laptop.setKodeBarang("B001");
        laptop.setNamaBarang("Lapppy toppy");
        laptop.setHargaBarang(10000000);
        BarangService.getInstance().addBarang(laptop);

        Barang mouse = new Barang();
        mouse.setKodeBarang("B002");
        mouse.setNamaBarang("Mousey mouse");
        mouse.setHargaBarang(120000);
        BarangService.getInstance().addBarang(mouse);
    }
}