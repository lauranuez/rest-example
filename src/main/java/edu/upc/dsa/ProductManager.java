package edu.upc.dsa;

import edu.upc.dsa.Classes.Order;
import edu.upc.dsa.Classes.Product;
import edu.upc.dsa.Classes.User;

import java.util.List;


public interface ProductManager {

    public List<Product> getProductByPrize();
    public List<Order> getOrdersByUser(String userId);
    public void newOrder(Order o);
    public Order processOrder();
    public List<Product> getProductBySales();
    public void clear();
    //public User getUser(String userId);
    public void addProduct(Product p);
    public void addUser(User user);
    public void dirty();
    public boolean isDirty();

}
