package edu.upc.dsa.Classes;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String userId;
    private List <Order> listOrdersDone= new ArrayList<Order>();
    public User()
    {

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public User(String name)
    {
        this.userId=name;
    }


    public void addOrderDone(Order order) {
        listOrdersDone.add(order);
    }

    public String getId() {return userId;}
    public void setId(String userId) {this.userId = userId;}
    public List<Order> getListOrdersDone() {return listOrdersDone; }
    public void setListOrdersDone(List<Order> listOrdersDone) {this.listOrdersDone = listOrdersDone;}
    public String toString() {
        return "User [name="+userId+ "]";
    }






}
