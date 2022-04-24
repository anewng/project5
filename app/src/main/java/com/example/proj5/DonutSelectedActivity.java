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
    private String[] quantity = {"Select quantity", "1", "2", "3", "4", "5"};
    private ArrayAdapter<String> adapter;
    private Button addToOrder;
    private Donut donut;

    private View.OnClickListener addToOrderOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            addToOrderButtonClicked();
        }
    };

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

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                updateSubtotal();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
        addToOrder = findViewById(R.id.addToOrder);
        addToOrder.setOnClickListener(addToOrderOnClickListener);
    }

    private void addToOrderButtonClicked() {
        if(spinner.getSelectedItemPosition() == 0) return;

        /*String flavor = donutFlavorText.getText().toString();
        String type = getDonutType(flavor);
        if (type.compareTo("CakeDonut") == 0) {
            donut = new CakeDonut(flavor);
        } else if (type.compareTo("DonutHole") == 0) {
            donut = new DonutHole(flavor);
        } else {
            donut = new YeastDonut(flavor);
        }*/
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

    private void updateSubtotal() {
        double subtotal = 0;
        if (spinner.getSelectedItem().toString() != "Select quantity") {
            String type = getDonutType(donutFlavorText.getText().toString());
            if (type.compareTo("Cake Donut") == 0) {
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
