package com.example.proj5;

/**
 The MenuItem class is used to create, manipulate, and access MenuItem objects.
 MenuItems can be created, and their quantities can be set and accessed.
 The MenuItem can be converted to a string.
 @author Annie Wang, Jasmine Flanders
 */
public abstract class MenuItem {

    private int quantity;
    private static final String INVALID_TYPE = "";

    public abstract double itemPrice();

    /**
     Converts a menu item to a string, depending on if it is a donut or coffee item
     @return string representation of the menu item.
     */
    @Override
    public String toString() {
        if (this instanceof Coffee) {
            Coffee coffee = (Coffee) this;
            return coffee.toString();
        } else if (this instanceof Donut) {
            Donut donut = (Donut) this;
            return donut.toString();
        } else {
            return INVALID_TYPE;
        }
    }

    /**
     Sets the quantity of the Menu Item
     */
    public void setQuantity(int amount){
        quantity = amount;
    }

    /**
     Gets the quantity of the Menu Item
     @return int returns the quantity
     */
    public int getQuantity(){
        return quantity;
    }


}
