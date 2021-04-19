package edu.upc.dsa;

import edu.upc.dsa.Classes.Order;
import edu.upc.dsa.Classes.Product;
import edu.upc.dsa.Classes.User;
import edu.upc.dsa.ProductManager;
import edu.upc.dsa.ProductManagerimpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ProductManagerTest {

    ProductManager pm = ProductManagerimpl.getInstance();
    private Order o1;
    private Order o2;
    private Order o3;



    @Before
    public void init(){

        Product product1 = new Product("coca", 2);
        Product product2 = new Product("pan", 1);
        Product product3 = new Product("bocadillo de lomo", 4);
        Product product4 = new Product("patatas", 3);

        pm.addProduct(product1);
        pm.addProduct(product2);
        pm.addProduct(product3);
        pm.addProduct(product4);

        User user = new User("Juan");
        User user2 = new User("Maria");

        pm.addUser(user);
        pm.addUser(user2);

        o1 = new Order("Maria");
        o2 = new Order("Juan");
        o3 = new Order("Juan");
        o1.addProduct("coca");
        o1.addProduct("coca");
        o1.addProduct("pan");

        pm.newOrder(o1);

        o2.addProduct("coca");
        o2.addProduct("bocadillo de lomo");
        o2.addProduct("pan");

        pm.newOrder(o2);

        o3.addProduct("coca");
        o3.addProduct("coca");
        o3.addProduct("patatas");

        pm.newOrder(o3);

    }

    @After
    public void reset(){
        pm.clear();
    }


    @Test
    public void test1(){
        List<Product> orden = pm.getProductByPrize();
        Assert.assertEquals("pan", orden.get(0).getName());
    }
    @Test
    public void test2(){
        //User maria = pm.getUser("Maria");
        //Assert.assertNotNull(maria);
        List<Product> orden = pm.getProductBySales();
        Assert.assertEquals("coca", orden.get(0).getName());
    }
    @Test
    public void test3() {
        Order o4 = pm.processOrder();
        Assert.assertEquals(o1.getId(), o4.getId());
    }
    @Test
    public void test4(){
        pm.processOrder();
        pm.processOrder();
        pm.processOrder();
        List<Product> orden = pm.getProductBySales();
        Assert.assertEquals("pan",orden.get(1).getName());
    }
    @Test
    public void test5(){
        pm.processOrder();
        Order o5 = pm.processOrder();
        pm.processOrder();
        List<Order> order = pm.getOrdersByUser("Juan");
        Assert.assertEquals(order.get(0).getId(),o5.getId());
        Assert.assertEquals(2,order.size());
    }
}
