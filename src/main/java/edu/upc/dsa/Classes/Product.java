package edu.upc.dsa.Classes;

public class Product {


    private String name;
    private float price;
    private int sales = 0;

    public Product(String name, float price) {
        this.name = name;
        this.price = price;

    }

    public void addSale() {

        this.sales++;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    public void setSales(int sales) { this.sales = sales; }
    public float getPrice() {
        return price;
    }
    public int getSales() {
        return sales;
    }
    public String getName() {
        return name;
    }
    public String toString() {
        return "Product [name="+name+", price=" + price + ", sales=" + sales +"]";
    }





}
