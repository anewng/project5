package com.example.proj5;

/**
 The YeastDonut class is used to create, manipulate, and access YeastDonut objects.
 A yeast donut is a type of donut.
 A yeast donut's price can be accessed, and the yeast donut can be converted to a string.
 @author Annie Wang, Jasmine Flanders
 */
public class YeastDonut extends Donut{

    private static final String FLAVOR_1 = "Fungi";
    private static final String FLAVOR_2 = "E coli";
    private static final String FLAVOR_3 = "Salmonella";

    private static final double YEAST_PRICE = 1.59;

    /**
     Constructor creates a YeastDonut object.
     @param flavor the flavor of the Yeast Donut
     */
    public YeastDonut(String flavor) {
        super(flavor);
    }

    /**
     Returns the raw price of the yeast donut, excluding taxes
     @return double the value of the price.
     */
    @Override
    public double itemPrice() {
        return YEAST_PRICE;
    }

    /**
     Converts a yeast donut to a string, with type of donut, flavor, and quantity.
     @return string representation of yeast donut.
     */
    @Override
    public String toString() {
        return "Yeast Donut, " + getFlavor() + " (" + getQuantity() + ")";
    }

}
