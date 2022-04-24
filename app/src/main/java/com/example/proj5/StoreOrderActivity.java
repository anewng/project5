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

public class StoreOrderActivity extends AppCompatActivity {

    public static ArrayList<Order> orders = new ArrayList<Order>();
    private String [] orderArray;
    private ArrayAdapter<String> adapter;
    private ListView storeOrders;
    private static final double SALES_TAX = 0.06625;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_order);
        Intent intent = getIntent();

        storeOrders = findViewById(R.id.storeOrderDisplay);
        updateListView();

        storeOrders.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AlertDialog.Builder alert = new AlertDialog.Builder(StoreOrderActivity.this);
                alert.setTitle("Delete an Order");
                alert.setMessage("delete order?");
                //anonymous inner class to handle the onClick event of YES or NO.
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "Deleted Order", Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });
    }

    /**
     Updates the list view display with all items in the current order.
     */
    public void updateListView(){
        orderArray = new String[orders.size()];
        DecimalFormat d = new DecimalFormat("'$'#,##0.00");
        for (int i = 0; i < orders.size(); i++){
            double subtotal = orders.get(i).getSubtotal();
            double salesTax = subtotal * SALES_TAX;
            orderArray[i] = "Order #" + (i+1) + orders.get(i).toString() + "subtotal: "
                    + d.format(subtotal) +  "\nsales tax: " + d.format(salesTax)
                    + "\ntotal: " + d.format(subtotal + salesTax);
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, orderArray);
        storeOrders.setAdapter(adapter);
    }
}
