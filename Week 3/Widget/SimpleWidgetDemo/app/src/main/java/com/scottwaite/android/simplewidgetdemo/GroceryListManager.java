/*
Created By: Scott Waite
Course: MDF III
Instructor: Michael Celey
Assignment: Building a Widget
Date: May 22, 2015
*/

package com.scottwaite.android.simplewidgetdemo;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class GroceryListManager {
    private static GroceryListManager instance = null;
    private ArrayList<GroceryItem> groceryList = null;
    private Context appContext = null;

    private GroceryListManager(Context context) {
        appContext = context;
        groceryList = RetrieveList();
    }

    public static GroceryListManager getInstanceWithContext(Context context) {
        if (instance == null) {
            instance = new GroceryListManager(context);
        }
        return instance;
    }

    public void addNewItem(GroceryItem item) {
        groceryList.add(item);
        SaveList();
    }

    public ArrayList<GroceryItem> getGroceryList() {
        return groceryList;
    }

    private void SaveList() {
        try {
            File dir = new File(appContext.getFilesDir().getAbsolutePath() + File.separator + "groceryList");
            if (!dir.exists()) {
                dir.mkdir();
            }
            FileOutputStream fos = new FileOutputStream(dir + File.separator + "list.txt");
            ObjectOutput oo = new ObjectOutputStream(fos);
            oo.writeObject(groceryList);
            oo.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ArrayList<GroceryItem> RetrieveList() {
        ArrayList<GroceryItem> list = null;
        try {
            File dir = new File(appContext.getFilesDir().getAbsolutePath() + File.separator + "groceryList");
            if (!dir.exists()) {

                list = new ArrayList<GroceryItem>();
            }
            FileInputStream fis = new FileInputStream(dir + File.separator + "list.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            list =  (ArrayList<GroceryItem>)ois.readObject();

            // to delete data, uncomment following line, run once, then comment line again
            // boolean wasDeleted = dir.delete(); list = new ArrayList<GroceryItem>();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
