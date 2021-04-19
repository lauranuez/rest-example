package edu.upc.dsa;

import edu.upc.dsa.Classes.Order;
import edu.upc.dsa.Classes.Product;
import edu.upc.dsa.Classes.User;
import org.apache.log4j.Logger;

import java.util.*;

public class ProductManagerimpl implements ProductManager {

    private Queue<Order> pendingOrder = new LinkedList<>();
    private List<Product> listProduct;
    private HashMap<String, User > hmUsers;
    private int cont = 0;
    final static Logger logger = Logger.getLogger(TracksManagerImpl.class);

    private static ProductManager instance;

   private ProductManagerimpl () {
       listProduct = new ArrayList<>();
       hmUsers = new HashMap<String,User>();
   }

   public void addProduct(Product p){
       logger.info("new Product " + p.getName());
       listProduct.add(p);
       logger.info("new Product added");
   }

   public void addUser(User user){
       logger.info("new user " + user.getId());
       hmUsers.put(user.getId(),user);
       logger.info("new user added");
   }

   public void clear(){

       listProduct.clear();
       hmUsers.clear();
       pendingOrder.clear();

   }
/*
    @Override
    public User getUser(String userId) {
        return hmUsers.get(userId);
    }
*/
    public static ProductManager getInstance() {
        if (instance==null) instance = new ProductManagerimpl();
        return instance;
    }

    public List<Product> getListProduct(){return listProduct;}

    @Override
    public List<Product> getProductByPrize() {
        List<Product> p = listProduct;
        /*
        int cont = 0;
        Product product = new Product(" ",0);
        for (int i = 0; i < p.size()-1; i++){
            for (int j = i+1; j <p.size(); j++){
                if (p.get(i).getPrice()>p.get(j).getPrice()) {
                    product = p.get(i);
                    p.set(i,p.get(j));
                    p.set(j, product);
                }
            }
        }
*/
        logger.info("getProductbyPrize()");
        Collections.sort(p, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
               // return (int)(o2.getPrice()-o1.getPrice());
                return Float.compare(o1.getPrice(),o2.getPrice());
            }
        });
        logger.info("the first product is: " + p.get(0).getName());
        return p;
    }
    @Override
    public List<Order> getOrdersByUser(String userId) {
        logger.info("getOrdersbySale()");
        if (hmUsers.get(userId)==null)
        {
            logger.warn("user"+ userId + "not found ");
        }
        else if(hmUsers.get(userId).getListOrdersDone()==null){
            logger.warn("list empty: the user don't have made any order");
        }
        else if (hmUsers.get(userId).getListOrdersDone()!=null){
            logger.info("the first order is: "+ hmUsers.get(userId).getListOrdersDone().get(0).getId());
        }

        return hmUsers.get(userId).getListOrdersDone();

    }

    @Override
    public void newOrder(Order o) {
        logger.info("newOrder()");
        User user = hmUsers.get(o.getUserName());;
        user.addOrder(o);
        pendingOrder.add(o);
        o.setId(cont);
        cont++;
        logger.info("order added");
    }
    @Override
    public Order processOrder() {
        logger.info("processOrder()");
        Order o = pendingOrder.remove();
        logger.info("the order " + o.getId() + "has been processed" );
        User user = hmUsers.get(o.getUserName());
        user.addOrderDone(o);
        logger.info("the order has been added to " + o.getUserName() + "order list" );
        List<String> p = o.getProductNames();
        for (int i = 0 ; i < p.size(); i++){
            for (int j = 0 ; j < listProduct.size(); j++){
                if (p.get(i) == listProduct.get(j).getName())
                    listProduct.get(j).addSale();
            }
        }
        return o;
    }
    @Override
    public List<Product> getProductBySales() {
        List<Product> p = listProduct;
        logger.info("getProductBySale()");

        /*
        int cont = 0;
        Product product = new Product(" ",0);
        for (int i = 0; i < p.size()-1; i++){
            for (int j = i+1; j <p.size(); j++){
                if (p.get(i).getSales()<p.get(j).getSales()) {
                    product = p.get(i);
                    p.set(i,p.get(j));
                    p.set(j, product);
                }
            }
        }
        return p;

         */
        Collections.sort(p, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return o2.getSales()-o1.getSales();
            }
        });
        logger.info("the first product is: " + p.get(0).getName());
        return p;
    }
}
