package com.example.proj5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

/**
 The DonutSelectedActivity class allows the user to order a quantity of
 specific donuts in the selected donut flavor GUI.
 @author Annie Wang, Jasmine Flanders
 */
public class DonutSelectedActivity extends AppCompatActivity {
    private TextView donutFlavorText, donutSubtotal;
    private Spinner spinner;
    private AdapterView.OnItemSelectedListener spinnerOnClickListener
            = new AdapterView.OnItemSelectedListener() {
        /**
         Callback method to be invoked when an item in this view has been selected.
         @param adapterView The AdapterView where the selection happened
         @param view The view within the AdapterView that was clicked
         @param i The position of the view in the adapter
         @param l The row id of the item that is selected
         */
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            updateSubtotal();
        }
        /**
         Callback method to be invoked when the selection disappears from this view.
         @param adapterView The AdapterView that now contains no selected item.
         */
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    };
    private String[] quantity = {"Select quantity", "1", "2", "3", "4", "5"};
    private ArrayAdapter<String> adapter;
    private Button addToOrder;
    private View.OnClickListener addToOrderOnClickListener = new View.OnClickListener() {
        /**
         Anonymous inner class to implement the addToOrderOnClickListener method
         to register the listener
         @param v the current view that is being clicked
         */
        @Override
        public void onClick(View v) {
            addToOrderButtonClicked();
        }
    };
    private Donut donut;

    /**
     The onCreate method configures preliminary settings to clarify GUI interactions.
     @param savedInstanceState the Bundle object that stores information on the previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.donut_selected_activity);
        donutFlavorText = findViewById(R.id.donutFlavorText);
        Intent intent = getIntent();
        donutFlavorText.setText(intent.getStringExtra("ITEM"));
        donutSubtotal = findViewById(R.id.donutSubtotal);

        spinner = findViewById(R.id.quantitySpinner);
        adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, quantity);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(spinnerOnClickListener);
        addToOrder = findViewById(R.id.addToOrder);
        addToOrder.setOnClickListener(addToOrderOnClickListener);
    }

    /**
     Adds the chosen donut order to the cart.
     */
    private void addToOrderButtonClicked() {
        if (spinner.getSelectedItemPosition() == 0) {
            Context context = getApplicationContext();
            CharSequence text = "no quantity selected";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
            return;
        }

        donut.setQuantity(Integer.parseInt(spinner.getSelectedItem().toString()));
        OrderActivity.yourOrderArrayList.getOrderArray().add(donut);

        spinner.setSelection(0);
        updateSubtotal();

        Context context = getApplicationContext();
        CharSequence text = "Donuts added to order";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        finish();
    }

    /**
     Updates the subtotal of the donut order based on user interactions.
     */
    private void updateSubtotal() {
        double subtotal = 0;
        if (!spinner.getSelectedItem().toString().equals("Select quantity")) {
            System.out.println(donutFlavorText.getText().toString());
            String type = getDonutType(donutFlavorText.getText().toString());
            if (type.compareTo("CakeDonut") == 0) {
                donut = new CakeDonut(donutFlavorText.getText().toString());
            } else if (type.compareTo("DonutHole") == 0) {
                donut = new DonutHole(donutFlavorText.getText().toString());
            } else {
                donut = new YeastDonut(donutFlavorText.getText().toString());
            }
            subtotal += (Integer.parseInt(spinner.getSelectedItem().toString()) * donut.itemPrice());
        }
        DecimalFormat d = new DecimalFormat("'$'#,##0.00");
        donutSubtotal.setText(d.format(subtotal));
    }

    /**
     Sets the donut type based on user input in the GUI
     @return String the type of the donut based on the selected flavor
     */
    private String getDonutType(String flavor) {
        String type = "";
        if (flavor.compareTo("Red Velvet") == 0 || flavor.compareTo("Blueberry Chiffon") == 0|| flavor.compareTo("Raspberry Jam Swirl") == 0 || flavor.compareTo("Strawberry Shortcake") == 0) {
            type = "CakeDonut";
        } else if (flavor.compareTo("Yas") == 0 || flavor.compareTo("Slay") == 0 || flavor.compareTo("Purr") == 0 || flavor.compareTo("Periodt") == 0) {
            type = "DonutHole";
        } else {
            type = "YeastDonut";
        }
        return type;
    }

}
