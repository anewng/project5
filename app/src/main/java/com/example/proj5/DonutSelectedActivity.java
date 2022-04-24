package com.example.proj5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * For demonstration purpose, this class is the Activity to be started when an item on the
 * RecyclerView was clicked
 * Get the name of the item from an intent extra. The text of the button is set to the item name.
 * @author Lily Chang
 */
public class DonutSelectedActivity extends AppCompatActivity {
    private TextView donutFlavorText, donutSubtotal;
    private Spinner spinner;
    private String [] quantity = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    private ArrayAdapter<String> adapter;

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
    }

    private void updateSubtotal() {
        double subtotal = 0;
        String type = getDonutType(donutFlavorText.toString());
        Donut donut;
        if (type == "CakeDonut") {
            donut = new CakeDonut(donutFlavorText.toString());
        } else if (type == "DonutHole") {
            donut = new DonutHole(donutFlavorText.toString());
        } else {
            donut = new YeastDonut(donutFlavorText.toString());
        }
        subtotal += (Integer.parseInt(spinner.getSelectedItem().toString()) * donut.itemPrice());
        DecimalFormat d = new DecimalFormat("'$'#,##0.00");
        donutSubtotal.setText(d.format(subtotal));
    }

    private String getDonutType(String flavor) {
        if (flavor == "Red Velvet" || flavor == "Blueberry Chiffon" || flavor == "Raspberry Jam Swirl" || flavor == "Strawberry Shortcake") {
            return "CakeDonut";
        } else if (flavor == "Yas" || flavor == "Slay" || flavor == "Purr" || flavor == "Periodt") {
            return "DonutHole";
        } else {
            return "YeastDonut";
        }
    }
}
