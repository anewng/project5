package com.example.proj5;

import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

/**
 The StoreOrder class is used to create, manipulate, and access StoreOrder objects.
 StoreOrders can be created, and the corresponding array of orders can be set and accessed
 Orders can be added and removed from the store order.
 @author Annie Wang, Jasmine Flanders
 */
public class StoreOrders implements Customizable {

    private static ArrayList<Order> orders;

    /**
     Constructor creates a StoreOrder object.
     */
    public StoreOrders(){
        orders = new ArrayList<Order>();
    }

    /**
     Gets the arraylist corresponding to the StoreOrder.
     @return ArrayList<Order> the arraylist of orders
     */
    public ArrayList<Order> getOrders(){
        return orders;
    }

    /**
     Adds an order to current store order
     @return boolean denoting if the order was successfully added or not.
     */
    @Override
    public boolean addObject(Object obj) {
        Order newOrder = (Order) obj;
        int lastIndex = orders.size() - 1;
        if(orders.size() == 0){
            newOrder.setOrderNumber(1);
        }else{
            newOrder.setOrderNumber(orders.get(lastIndex).getOrderNumber() + 1);
        }
        orders.add(newOrder);
        return true;
    }

    /**
     Removes an order from the store order
     @return boolean denoting if the order was successfully removed or not.
     */
    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Order) {
            Order removeOrder = (Order) obj;
            int orderNumber = removeOrder.getOrderNumber();
            for(int i = 0; i < orders.size(); i++){
                if(orders.get(i).getOrderNumber() == orderNumber){
                    orders.remove(i);
                    return true;
                }
            }
            return false;
        } else {
            return false;
        }
    }
}



