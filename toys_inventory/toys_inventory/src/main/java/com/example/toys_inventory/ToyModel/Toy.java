package com.example.toys_inventory.ToyModel;

public class Toy {
    private String toyID;
    private String toyBrand;
    private String toyName;
    private int qtyStart;
    private int qtySold;
    private double unitPrice;


    public Toy() {

    }

    public Toy(String toyID, String toyBrand, String toyName, int qtyStart, int qtySold, double unitPrice) {
        this.toyID = toyID;
        this.toyBrand = toyBrand;
        this.toyName = toyName;
        this.qtyStart = qtyStart;
        this.qtySold = qtySold;
        this.unitPrice = unitPrice;

    }

    public String getToyID() {
        return toyID;
    }

    public void setToyID(String toyID) {
        this.toyID = toyID;
    }

    public String getToyBrand() {
        return toyBrand;
    }

    public void setToyBrand(String toyBrand) {
        this.toyBrand = toyBrand;
    }

    public String getToyName() {
        return toyName;
    }

    public void setToyName(String toyName) {
        this.toyName = toyName;
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

    public int qtyOnHand() {
        return qtyStart - qtySold;
    }

    public double totalSales () {
        return qtySold * unitPrice;
    }

    @Override
    public String toString() {
        return "Toy{" +
                "toyID='" + toyID + '\'' +
                ", toyBrand='" + toyBrand + '\'' +
                ", toyName='" + toyName + '\'' +
                ", qtyStart=" + qtyStart +
                ", qtySold=" + qtySold +
                ", qtyOnHand=" + qtyOnHand() +
                ", unitPrice=" + unitPrice +
                ", totalSales=" + totalSales() +
                '}';
    }
}
