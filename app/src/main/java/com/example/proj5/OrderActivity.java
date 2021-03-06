package com.example.proj5;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;
import java.util.StringTokenizer;

/**
 The OrderActivity class dictates the function of the order activity GUI.
 @author Annie Wang, Jasmine Flanders
 */
public class OrderActivity extends AppCompatActivity {
    private static final double SALES_TAX = 0.06625;
    private static final int AUTOMATIC_REMOVAL_INDEX = -1;

    public static Order yourOrderArrayList = new Order();
    private String [] yourOrderArray;
    private ArrayAdapter<String> adapter;
    private ListView yourOrders;
    private AdapterView.OnItemClickListener yourOrdersOnClickListener
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
            AlertDialog.Builder alert = new AlertDialog.Builder(OrderActivity.this);
            alert.setTitle("Delete an Item");
            alert.setMessage("delete item?");
            alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "Deleted Item", Toast.LENGTH_LONG).show();
                    removeSelected(i);
                }
            }).setNegativeButton("no", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                }
            });
            AlertDialog dialog = alert.create();
            dialog.show();
        }
    };
    private TextView subTotal, salesTax, total;
    private Button placeOrder;
    private View.OnClickListener placeOrderOnClickListener = new View.OnClickListener() {
        /**
         Anonymous inner class to implement the placeOrderOnClickListener method
         to register the listener
         @param v the current view that is being clicked
         */
        @Override
        public void onClick(View v) {
            if(yourOrderArrayList.getOrderArray().size() == 0) {
                Context context = getApplicationContext();
                CharSequence text = "cart is empty";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
                return;
            }
            placeOrderButtonClicked();
        }
    };

    /**
     The onCreate method configures preliminary settings to clarify GUI interactions.
     @param savedInstanceState the Bundle object that stores information on the previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        yourOrders = (ListView) findViewById(R.id.yourOrders);
        subTotal = findViewById(R.id.orderSubTotal);
        salesTax = findViewById(R.id.orderSalesTax);
        total = findViewById(R.id.orderTotal);

        updateListView();
        updateTotals();

        placeOrder = findViewById(R.id.placeOrder);
        placeOrder.setOnClickListener(placeOrderOnClickListener);
        yourOrders.setOnItemClickListener(yourOrdersOnClickListener);
    }

    /**
     Places the order into the directory of store orders and outputs a confirmation message.
     */
    private void placeOrderButtonClicked() {
        if(yourOrderArrayList.getOrderArray().size() == 0) return;

        StoreOrderActivity.orders.getOrders().add(yourOrderArrayList);

        yourOrderArrayList = new Order();
        updateListView();
        updateTotals();

        Context context = getApplicationContext();
        CharSequence text = "Placed order";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        finish();
    }

    /**
     Updates the list view display with all items in the current order.
     */
    public void updateListView(){
        yourOrderArray = new String[yourOrderArrayList.getOrderArray().size()];
        for (int i = 0; i < yourOrderArrayList.getOrderArray().size(); i++){
            yourOrderArray[i] = yourOrderArrayList.getOrderArray().get(i).toString();
        }

        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, yourOrderArray);
        yourOrders.setAdapter(adapter);
    }

    /**
     Updates the subtotal, tax, and total text fields with calculated prices.
     */
    public void updateTotals(){
        double subtotalDouble = 0;
        for (int i = 0; i < yourOrderArrayList.getOrderArray().size(); i++) {
            subtotalDouble += yourOrderArrayList.getOrderArray().get(i).itemPrice()
                    * yourOrderArrayList.getOrderArray().get(i).getQuantity();
        }
        DecimalFormat d = new DecimalFormat("'$'#,##0.00");
        String subtotalString = d.format(subtotalDouble);
        subTotal.setText(subtotalString);

        double taxDouble = subtotalDouble * SALES_TAX;
        String taxString = d.format(taxDouble);
        salesTax.setText(taxString);

        double totalDouble = subtotalDouble + taxDouble;
        String totalString = d.format(totalDouble);
        total.setText(totalString);
    }

    /**
     Removes menu item from the order based on user input in the GUI
     @param position
     */
    private void removeSelected(int position) {
        StringTokenizer string = new StringTokenizer(yourOrders.getItemAtPosition(position).toString());
        String flavorSizeToken = "";
        String itemType = setItemType(string.nextToken());

        if(itemType.equals("Coffee")){
            flavorSizeToken = string.nextToken(); // getting coffee flavor
        } else if(itemType.equals("invalid item type")) {
            return;
        } else {
            string.nextToken();
            flavorSizeToken = string.nextToken(); //getting donut flavor
            flavorSizeToken = setDonutFlavor(flavorSizeToken);
        }

        int removalIndex = findRemovalIndex(itemType, flavorSizeToken);
        yourOrderArrayList.getOrderArray().remove(removalIndex);

        updateListView();
        updateTotals();

    }

    /**
     Searches the order list to find the index that corresponds with the item type and flavor size token
     @param itemType indicates if the item is Coffee or Donut
     @param flavorSizeToken indicates the flavor or size based on if the item is Coffee or Donut
     @return int the index of the item, if found. if not found, returns the automatic removal index
     */
    private int findRemovalIndex(String itemType, String flavorSizeToken){
        int removalIndex = AUTOMATIC_REMOVAL_INDEX;
        for(int i = 0; i < yourOrderArrayList.getOrderArray().size(); i++){
            if(yourOrderArrayList.getOrderArray().get(i) instanceof Coffee && itemType.equals("Coffee")
                    && ((Coffee) yourOrderArrayList.getOrderArray().get(i)).getSize().equals(flavorSizeToken)){
                removalIndex = i;
            } else if(yourOrderArrayList.getOrderArray().get(i) instanceof Donut && itemType.equals("Donut")
                    && ((Donut) yourOrderArrayList.getOrderArray().get(i)).getFlavor().equals(flavorSizeToken)){
                removalIndex = i;
            }
        }
        return removalIndex;
    }

    /**
     Identifies and sets the menu item type based on the first token
     @param firstToken the token that indicates the specific menu item
     @return String the full menu item type
     */
    private String setItemType(String firstToken){
        if(firstToken.equals("Donut") || firstToken.equals("Yeast") || firstToken.equals("Cake")){
            return "Donut";
        } else if (firstToken.equals("Coffee,")) {
            return "Coffee";
        } else {
            return "invalid item type";
        }
    }

    /**
     Identifies and sets the donut flavor based on the third token
     @param thirdToken the token that indicates the specific donut flavor
     @return String the full donut flavor
     */
    private String setDonutFlavor(String thirdToken){
        if(thirdToken.equals("E")){
            return "E coli";
        } else if (thirdToken.equals("Red")) {
            return "Red Velvet";
        } else if (thirdToken.equals("Blueberry")) {
            return "Blueberry Chiffon";
        } else if (thirdToken.equals("Raspberry")) {
            return "Raspberry Jam Swirl";
        } else if (thirdToken.equals("Strawberry")) {
            return "Strawberry Shortcake";
        } else if (thirdToken.equals("Yeast")) {
            return "Yeast Infection";
        } else {
            return thirdToken;
        }
    }
}
