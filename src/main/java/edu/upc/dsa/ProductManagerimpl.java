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

   public User getUser(String name){

       return hmUsers.get(name);
   }

   public Product getProduct(String name){
       return null;
   }

   public void clear(){

       listProduct.clear();
       hmUsers.clear();
       pendingOrder.clear();

   }

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
        return hmUsers.get(userId).getListOrdersDone();
    }
    @Override
    public void newOrder(Order o) {
        User user = hmUsers.get(o.getUserName());
        if (user == null)
            System.out.println("usuari " + o.getUserName() + " no existeix");
        user.addOrder(o);
        pendingOrder.add(o);

        o.setId(cont);
        cont++;
    }
    @Override
    public Order processOrder() {
        Order o = pendingOrder.remove();
        User user = hmUsers.get(o.getUserName());
        user.addOrderDone(o);

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

        return p;
    }
}
