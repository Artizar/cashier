package com.smk.cashier.model;

public class Stock  extends Model{
    private int id;

    private String kodeBarang;

    private int stockBarang;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getKodeBarang() {
        return kodeBarang;
    }

    public void setKodeBarang(String kodeBarang) {
        this.kodeBarang = kodeBarang;
    }

    public int getStockBarang() {
        return stockBarang;
    }

    public void setStockBarang(int stockBarang) {
        this.stockBarang = stockBarang;
    }

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", kodeBarang='" + kodeBarang + '\'' +
                ", stockBarang=" + stockBarang +
                ", dateCreated=" + dateCreated +
                ", lastModified=" + lastModified +
                ", createdBy='" + createdBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                '}';
    }
}
