package com.example.proj5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class CoffeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{
    private Spinner spinner;
    private String [] sizes = {"Short", "Tall", "Grande", "Venti"};
    private ArrayAdapter<String> adapter;
    private Coffee coffee = new Coffee();
    private CheckBox milk, cream, syrup, caramel, whippedCream;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        Intent intent = getIntent();
        spinner = findViewById(R.id.sizeSpinner);
        adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, sizes);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            //do stuff
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        milk = findViewById(R.id.milk);
        cream = findViewById(R.id.cream);
        syrup = findViewById(R.id.syrup);
        caramel = findViewById(R.id.caramel);
        whippedCream = findViewById(R.id.whippedCream);
        milk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (milk.isSelected()) {
                    coffee.addObject("milk");
                } else if (!milk.isSelected()){
                    coffee.remove("milk");
                }
                updateSubtotalAndCoffee();
            }
        });
        cream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cream.isSelected()) {
                    coffee.addObject("cream");
                } else if (!cream.isSelected()){
                    coffee.remove("cream");
                }
                updateSubtotalAndCoffee();
            }
        });
        syrup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (syrup.isSelected()) {
                    coffee.addObject("syrup");
                } else if (!syrup.isSelected()){
                    coffee.remove("syrup");
                }
                updateSubtotalAndCoffee();
            }
        });
        caramel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (milk.isSelected()) {
                    coffee.addObject("caramel");
                } else if (!caramel.isSelected()){
                    coffee.remove("caramel");
                }
                updateSubtotalAndCoffee();
            }
        });
        whippedCream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (whippedCream.isSelected()) {
                    coffee.addObject("whipped cream");
                } else if (!whippedCream.isSelected()){
                    coffee.remove("whipped cream");
                }
                updateSubtotalAndCoffee();
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }


    /**
     Updates the subtotal text field and coffee order based on changes in the add-on checkboxes
     */
    private void updateSubtotalAndCoffee() {
        DecimalFormat d = new DecimalFormat("'$'#,##0.00");
            coffee.setSize(spinner.getSelectedItem().toString());
        double subTotal;
        TextView subtotalText = (TextView) findViewById(R.id.coffeeSubtotal);
        subTotal = coffee.itemPrice();
        subtotalText.setText(d.format(subTotal));
    }
}
