package edu.upc.dsa.Classes;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String userId;
    private List <Order> listOrders = new ArrayList<Order>();
    private List <Order> listOrdersDone= new ArrayList<Order>();

    public User(String name)
    {
        this.userId=name;
    }

    public void addOrder(Order order) {
        listOrders.add(order);
    }
    public void addOrderDone(Order order) {
        listOrdersDone.add(order);
    }

    public List<Order> getListOrders() {return listOrders; }
    public void setListOrders(List<Order> listOrders) {this.listOrders = listOrders; }
    public String getId() {return userId;}
    public void setId(String userId) {this.userId = userId;}
    public List<Order> getListOrdersDone() {return listOrdersDone; }
    public void setListOrdersDone(List<Order> listOrdersDone) {this.listOrdersDone = listOrdersDone;}
    public String toString() {
        return "User [name="+userId+ "]";
    }






}
