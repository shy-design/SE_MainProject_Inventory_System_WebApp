package com.example.toys_inventory.DataModel;

import javax.persistence.*;

@Entity
@Table(name = "games")
public class Game {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String brand;
    private String name;
    private int qtyStart;
    private int qtySold;
    private double unitPrice;

    public Game() {

    }

    public Game(int id, String brand, String name, int qtyStart, int qtySold, double unitPrice) {
        this.id = id;
        this.brand = brand;
        this.name = name;
        this.qtyStart = qtyStart;
        this.qtySold = qtySold;
        this.unitPrice = unitPrice;
    }


    public int qtyOnHand() {
        return qtyStart - qtySold;
    }

    public double totalSales () {
        return qtySold * unitPrice;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQtyStart() {
        return qtyStart;
    }

    public void setQtyStart(int qtyStart) {
        this.qtyStart = qtyStart;
    }

    public int getQtySold() {
        return qtySold;
    }

    public void setQtySold(int qtySold) {
        this.qtySold = qtySold;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "Game{" +
                "id='" + id + '\'' +
                ", brand='" + brand + '\'' +
                ", name='" + name + '\'' +
                ", qtyStart=" + qtyStart +
                ", qtySold=" + qtySold +
                ", qtyOnHand=" + qtyOnHand() +
                ", unitPrice=" + unitPrice +
                ", totalSales=" + totalSales() +
                '}';
    }
}
