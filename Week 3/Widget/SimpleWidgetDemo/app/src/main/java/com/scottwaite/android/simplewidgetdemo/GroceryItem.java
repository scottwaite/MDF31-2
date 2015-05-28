package com.scottwaite.android.simplewidgetdemo;

import java.io.Serializable;

/**
 * Created by scottwaite on 5/27/15.
 */
public class GroceryItem implements Serializable {

    public String name;

    public int quantity;

    public int aisle;

    public GroceryItem (String name, int quantity, int aisle ) {
        this.name = name;
        this.quantity = quantity;
        this.aisle = aisle;
    }
}


