/*
Created By: Scott Waite
Course: MDF III
Instructor: Michael Celey
Assignment: Building a Widget
Date: May 22, 2015
*/

package com.scottwaite.android.simplewidgetdemo;

import java.io.Serializable;

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


