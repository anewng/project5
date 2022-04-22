package com.example.proj5;

/**
 The Customizable interface provides the behavior of classes that implements it to add and remove objects.
 @author Annie Wang, Jasmine Flanders
 */
public interface Customizable {
    /**
     Denotes the behavior of adding objects
     @param obj the object that is to be added
     */
    boolean addObject(Object obj);

    /**
     Denotes the behavior of removing objects
     @param obj the object that is to be removed
     */
    boolean remove(Object obj);
}
