package com.smk.cashier.service;

import com.smk.cashier.dao.BarangDao;
import com.smk.cashier.model.Barang;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

class BarangServiceTest {

    @Test
    @Order(2)
    void getBarangList() {
        List<Barang> bList = BarangService.getInstance().getBarangList();
        assertEquals(bList.size(), 3);
    }

    @Test
    @Order(3)
    void findByname(){
        List<Barang>  resultList = BarangService.getInstance().findByName("Laptop");
        assertEquals(resultList.size(), 2);
    }

    @Test
    @Order(1)
    void addBarang() {
        Barang laptop = new Barang();
        laptop.setKodeBarang("B001");
        laptop.setNamaBarang("Laptop");
        laptop.setHargaBarang(10000000);
        BarangService.getInstance().addBarang(laptop);

        Barang mouse = new Barang();
        mouse.setKodeBarang("B002");
        mouse.setNamaBarang("Mousey mouse");
        mouse.setHargaBarang(120000);
        BarangService.getInstance().addBarang(mouse);


        Barang laptopGaming = new Barang();
        laptopGaming.setKodeBarang("l002");
        laptopGaming.setNamaBarang("LaptopGaming");
        laptopGaming.setHargaBarang(20);
        BarangService.getInstance().addBarang(laptopGaming);
    }

    @Test
    @Order(4)
    void saveBarangToDatabase(){
        BarangDao barangDao = new BarangDao();
        Barang laptop = new Barang();
        laptop.setKodeBarang("B001");
        laptop.setNamaBarang("Laptop");
        laptop.setHargaBarang(10000000);
        laptop.setDateCreated(new Date());
        laptop.setLastModified(new Date());
        barangDao.save(laptop);

        Barang mouse = new Barang();
        mouse.setKodeBarang("B002");
        mouse.setNamaBarang("Mousey mouse");
        mouse.setHargaBarang(120000);
        mouse.setDateCreated(new Date());
        mouse.setLastModified(new Date());
        barangDao.save(mouse);


        Barang laptopGaming = new Barang();
        laptopGaming.setKodeBarang("l002");
        laptopGaming.setNamaBarang("LaptopGaming");
        laptopGaming.setHargaBarang(20);
        laptopGaming.setDateCreated(new Date());
        laptopGaming.setLastModified(new Date());
        barangDao.save(laptopGaming);
    }

    @Test
    @Order(5)
    void getDataById(){
        BarangDao barangDao = new BarangDao();
        Optional<Barang> barang1 = barangDao.get(1);
        barang1.ifPresent(new Consumer<Barang>() {
            @Override
            public void accept(Barang barang) {
                assertEquals("Laptop", barang.getNamaBarang());
                assertEquals("B001", barang.getKodeBarang());
            }
        });

        Optional<Barang> barang2 = barangDao.get(2);
        barang2.ifPresent(new Consumer<Barang>() {
            @Override
            public void accept(Barang barang) {
                assertEquals("Mousey mouse", barang.getNamaBarang());
                assertEquals("B002", barang.getKodeBarang());
            }
        });

        Optional<Barang> barang3 = barangDao.get(3);
        barang3.ifPresent(new Consumer<Barang>() {
            @Override
            public void accept(Barang barang) {
                assertEquals("LaptopGaming", barang.getNamaBarang());
                assertEquals("l002", barang.getKodeBarang());
            }
        });
    }

    @Test
    @Order(6)
    void updateBarangByKodeBarang(){
     BarangDao barangDao = new BarangDao();
        Barang laptop = new Barang();
        laptop.setKodeBarang("B001");
        laptop.setNamaBarang("Laptop Updated");
        laptop.setHargaBarang(60000000);
        barangDao.update(laptop);

        Optional<Barang> barang1 = barangDao.get(1);
        barang1.ifPresent(new Consumer<Barang>() {
            @Override
            public void accept(Barang barang) {
                assertEquals("Laptop Updated", barang.getNamaBarang());
                assertEquals("B001", barang.getKodeBarang());
                assertEquals(60000000, barang.getHargaBarang());
            }
        });
    }

    @Test
    @Order(7)
    void  deleteBarang(){
        BarangDao barangDao = new BarangDao();
        Barang laptop = new Barang();
        laptop.setKodeBarang("B001");
        barangDao.delete(laptop);
        Optional<Barang> barang1 = barangDao.get(1);
        assertEquals(barang1.isPresent(),false);
    }

    @Test
    @Order(8)
    void searchBarang(){
        BarangDao barangDao = new BarangDao();
        Barang BluetoothKeyboard = new Barang();
        BluetoothKeyboard.setNamaBarang("Bluetooth Keyboard");
        BluetoothKeyboard.setKodeBarang("BL001");
        BluetoothKeyboard.setHargaBarang(50000);
        BluetoothKeyboard.setDateCreated(new Date());
        BluetoothKeyboard.setLastModified(new Date());
        barangDao.save(BluetoothKeyboard);
       
        Barang Bluetoothmouse = new Barang();
        Bluetoothmouse.setNamaBarang("Bluetooth mouse");
        Bluetoothmouse.setKodeBarang("BL002");
        Bluetoothmouse.setHargaBarang(50000);
        Bluetoothmouse.setDateCreated(new Date());
        Bluetoothmouse.setLastModified(new Date());
        barangDao.save(Bluetoothmouse);

        Barang mechanicalKeyboard = new Barang();
        mechanicalKeyboard.setNamaBarang("mechanical Keyboard");
        mechanicalKeyboard.setKodeBarang("BL003");
        mechanicalKeyboard.setHargaBarang(50000);
        mechanicalKeyboard.setDateCreated(new Date());
        mechanicalKeyboard.setLastModified(new Date());
        barangDao.save(mechanicalKeyboard);

        assertEquals(barangDao.search("mecha").size(),1);
        assertEquals(barangDao.search("Key").size(),2);
        assertEquals(barangDao.search("BL").size(),3);

    }
}