package com.smk.cashier.dao;

import com.smk.cashier.model.Barang;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class BarangDao implements Dao<Barang, Integer> {
    private final Optional<Connection> connection;

    public BarangDao() {
        connection = JdbcConnection.getConnection();
    }

    @Override
    public Optional<Barang> get(int id) {
        return connection.flatMap(conn ->{
            Optional<Barang>barang = Optional.empty();
            String sql =  "SELECT * from barang where barang_id =?";
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1,id);
                ResultSet rs = ps.executeQuery();
                if(rs.next()) {
                    String kodeBarang = rs.getString("kode_barang");
                    String namaBarang = rs.getString("nama_barang");
                    int hargaBarang = rs.getInt("harga_baramg");
                    Barang barangResult = new Barang();
                    barangResult.setNamaBarang(kodeBarang);
                    barangResult.setNamaBarang(namaBarang);
                    barangResult.setHargaBarang(hargaBarang);
                    barang = Optional.of(barangResult);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return barang;
        });
    }

    @Override
    public Collection<Barang> getAll() {
        return null;
    }

    @Override
    public Optional<Integer> save(Barang barang) {
        Barang nonNullBarang = Objects.requireNonNull(barang);
        String sql = "INSERT INTO barang" + "(kode_barang, nama_barang, harga_barang, created_by, updated_by, date_created, last_modified )" + "VALUES(?,?,?,?,?,?,?)";
        return connection.flatMap(conn ->{
           Optional<Integer> generatedId = Optional.empty();
            try {
                PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, barang.getKodeBarang());
                ps.setString(2,barang.getNamaBarang());
                ps.setInt(3,barang.getHargaBarang());
                ps.setString(4,barang.getCreatedBy());
                ps.setString(5,barang.getUpdateBy());
                ps.setDate(6, new Date(barang.getDateCreated().getTime()));
                ps.setDate(7, new Date(barang.getLastModified().getTime()));
                int numberofInsertedRows = ps.executeUpdate();
                if (numberofInsertedRows > 0){
                    ResultSet rs = ps.getGeneratedKeys();
                    if (rs.next()){
                        Optional.of(rs.getInt(1));
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return generatedId;
        });
    }

    @Override
    public void update(Barang barang) {
        Barang nonNullBarang= Objects.requireNonNull(barang);
        String sql = "UPDATE barang " +
                "SET " +
                "harga_barang = ?," +
                "nama_barang = ?," +
                "last_modified = ?," +
                "updated_by = ? " +
                "WHERE " +
                "kode_barang = ? ";
        connection.ifPresent(conn ->{
            try {
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setInt(1,barang.getHargaBarang());
                ps.setString(2,barang.getNamaBarang());
                ps.setDate(3,new Date(new java.util.Date().getTime()));
                ps.setString(4,"test");
                ps.setString(5,barang.getKodeBarang());
                int numberOfUpdateRows = ps.executeUpdate();
                System.out.println("numberOfUpdateRows = " + numberOfUpdateRows);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

    }

    @Override
    public void delete(Barang barang) {

    }
}
