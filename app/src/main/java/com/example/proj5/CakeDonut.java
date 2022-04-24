package com.example.proj5;

/**
 The CakeDonut class is used to create, manipulate, and access CakeDonut objects.
 A cake donut is a subclass of donut.
 A cake donut's price can be accessed, and the cake donut can be converted to a string.
 @author Annie Wang, Jasmine Flanders
 */
public class CakeDonut extends Donut{

    private static final String FLAVOR_1 = "Red Velvet";
    private static final String FLAVOR_2 = "Blueberry Chiffon";
    private static final String FLAVOR_3 = "Raspberry Jam Swirl";
    private static final String FLAVOR_4 = "Strawberry Shortcake";

    private static final double CAKE_PRICE = 1.79;

    /**
     Constructor creates a CakeDonut object.
     @param flavor the flavor of the Cake Donut
     */
    public CakeDonut(String flavor) {
        super(flavor);
    }

    /**
     Returns the raw price of the cake donut, excluding taxes
     @return double the value of the price.
     */
    @Override
    public double itemPrice() {
        return CAKE_PRICE;
    }

    /**
     Converts a cake donut to a string, with type of donut, flavor, and quantity.
     @return string representation of cake donut.
     */
    @Override
    public String toString() {
        return "Cake Donut, " + getFlavor() + " (" + getQuantity() + ")";
    }

}
