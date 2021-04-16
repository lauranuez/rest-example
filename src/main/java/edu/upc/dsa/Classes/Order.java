package edu.upc.dsa.Classes;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<String> productNames = new ArrayList<String>();
    private String userName;
    private int id = 0;

    public Order(String user)
    {
        this.userName = user;
    }

    public void addProduct(String productName){

        productNames.add(productName);
    }

    public void setId(int id) { this.id = id; }
    public int getId() { return id;}
    public List<String> getProductNames() {
        return productNames;
    }
    public void setProductNames(List<String> names) {
        this.productNames = names;
    }
    public String getUserName() {
        return userName;
    }
    public String toString() {
        return "Order [comprador= "+userName+ "]";
    }

}
