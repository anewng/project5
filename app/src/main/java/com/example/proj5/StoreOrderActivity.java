package com.example.proj5;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 The StoreOrderActivity class dictates the function of the store order activity GUI.
 @author Annie Wang, Jasmine Flanders
 */
public class StoreOrderActivity extends AppCompatActivity {
    private static final double SALES_TAX = 0.06625;

    public static StoreOrders orders = new StoreOrders();
    private String [] orderArray;
    private ArrayAdapter<String> adapter;
    private ListView storeOrders;
    private AdapterView.OnItemClickListener storeOrdersOnClickListener
            = new AdapterView.OnItemClickListener() {
        /**
         Callback method to be invoked when an item in this view has been clicked.
         @param adapterView The AdapterView where the selection happened
         @param view The view within the AdapterView that was clicked
         @param i The position of the view in the adapter
         @param l The row id of the item that is selected
         */
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            AlertDialog.Builder alert = new AlertDialog.Builder(StoreOrderActivity.this);
            alert.setTitle("Delete an Order");
            alert.setMessage("delete order?");
            //anonymous inner class to handle the onClick event of YES or NO.
            alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "Deleted Order", Toast.LENGTH_LONG).show();
                    removeSelected(i);
                    finish();
                }
            }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            AlertDialog dialog = alert.create();
            dialog.show();
        }
    };

    /**
     The onCreate method configures preliminary settings to clarify GUI interactions.
     @param savedInstanceState the Bundle object that stores information on the previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_order);
        Intent intent = getIntent();

        storeOrders = findViewById(R.id.storeOrderDisplay);
        updateListView();
        storeOrders.setOnItemClickListener(storeOrdersOnClickListener);
    }

    /**
     Updates the list view display with all items in the current order.
     */
    public void updateListView(){
        orderArray = new String[orders.getOrders().size()];
        DecimalFormat d = new DecimalFormat("'$'#,##0.00");
        for (int i = 0; i < orders.getOrders().size(); i++){
            double subtotal = orders.getOrders().get(i).getSubtotal();
            double salesTax = subtotal * SALES_TAX;
            orderArray[i] = "ORDER #" + (i+1) + orders.getOrders().get(i).toString() + "Subtotal: "
                    + d.format(subtotal) +  "\nSales Tax: " + d.format(salesTax)
                    + "\nTotal: " + d.format(subtotal + salesTax);
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, orderArray);
        storeOrders.setAdapter(adapter);
    }

    /**
     Removes the selected item at the specified position and updates list view accordingly
     @param position the position of the selected item in the ListView
     */
    private void removeSelected(int position) {
        orders.getOrders().remove(position);
        updateListView();
    }
}
