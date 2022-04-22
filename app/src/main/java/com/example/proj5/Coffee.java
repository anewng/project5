package com.example.proj5;

import java.util.ArrayList;

/**
 The Coffee class is used to create, manipulate, and access Donut objects.
 It is a subclass of MenuItem, and implements the Customizable interface.
 A coffee's size and price accessed, and the coffee can also be converted to a string.
 Coffee addons can be added or removed.
 @author Annie Wang, Jasmine Flanders
 */
public class Coffee extends MenuItem implements Customizable{
    private static final double SHORT_PRICE = 1.69;
    private static final double TALL_PRICE = 2.09;
    private static final double GRANDE_PRICE = 2.49;
    private static final double VENTI_PRICE = 2.89;
    private static final double ADD_IN_PRICE = 0.30;

    private static final double INVALID_CASE = -1;


    private String size;
    private int addOnCount;
    private ArrayList<String> addOns = new ArrayList<String>();

    /**
     Returns the raw price of the coffee, excluding taxes
     @return double the value of the price.
     */
    @Override
    public double itemPrice() {
        if (this.size == "Short") {
            return SHORT_PRICE + (this.addOnCount * ADD_IN_PRICE);
        }
        if (this.size == "Tall") {
            return TALL_PRICE + (this.addOnCount * ADD_IN_PRICE);
        }
        if (this.size == "Grande") {
            return GRANDE_PRICE + (this.addOnCount * ADD_IN_PRICE);
        }
        if (this.size == "Venti") {
            return VENTI_PRICE + (this.addOnCount * ADD_IN_PRICE);
        }
        return INVALID_CASE;
    }

    /**
     Returns the coffee's size.
     @return string representing the size.
     */
    public String getSize() {
        return size;
    }

    /**
     Sets the coffee's size.
     @param size a String representing the size.
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     Converts a coffee to a string, with the type of item, size, and quantity.
     @return string representation of a coffee.
     */
    @Override
    public String toString() {
        String coffeeString = "Coffee, " + size + " (" + getQuantity() + ")";
        if(addOnCount != 0){
            coffeeString += ", add-ons: ";
        }
        for(int i = 0; i < addOnCount; i++){
            if(i == 0){
                coffeeString += addOns.get(i);
            } else {
                coffeeString += " | " + addOns.get(i);
            }
        }
        return coffeeString;
    }

    /**
     Adds an addon to the coffee order
     @return boolean denoting if the addon was successfully added or not.
     */
    @Override
    public boolean addObject(Object obj) {
        String addOn = (String) obj;
        addOns.add(addOn);
        addOnCount++;
        return true;
    }

    /**
     Removes an addon from the coffee order
     @return boolean denoting if the addon was successfully removed or not.
     */
    @Override
    public boolean remove(Object obj) {
        String addOn = (String) obj;
        for(int i = 0; i < addOns.size(); i++){
            if(addOns.get(i).equals(addOn)){
                addOns.remove(i);
                addOnCount--;
                return true;
            }
        }
        return false;
    }
}
