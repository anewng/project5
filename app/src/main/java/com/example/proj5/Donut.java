package com.example.proj5;

import java.io.Serializable;

/**
 The Donut class is used to create, manipulate, and access Donut objects.
 A donut item is a type of menu item.
 A donut's flavor and price can be accessed, and the donut can also be converted to a string.
 @author Annie Wang, Jasmine Flanders
 */
public abstract class Donut extends MenuItem implements Serializable {
    private String flavor;

    /**
     Constructor creates a Donut object.
     @param flavor the flavor of the Donut
     */
    public Donut(String flavor) {
        this.flavor = flavor;
    }

    /**
     Gets the flavor of the Cake Donut
     @return String the flavor
     */
    public String getFlavor() {
        return this.flavor;
    }

    /**
     Converts a donut to a string, the abstract method that will be overridden by its child class methods
     @return string representation of cake donut.
     */
    @Override
    public abstract String toString();

    /**
     Returns the donut raw price, the abstract method that will be overridden by its child class methods
     @return double cost of the item.
     */
    @Override
    public abstract double itemPrice();
}
