package com.example.proj5;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

public class CoffeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private String[] sizes = {"Select a size", "Short", "Tall", "Grande", "Venti"};
    private ArrayAdapter<String> adapter;
    private Coffee coffee = new Coffee();
    private CheckBox milk, cream, syrup, caramel, whippedCream;
    private Button clickButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spinner = findViewById(R.id.sizeSpinner);
        setContentView(R.layout.activity_coffee);
        Intent intent = getIntent();
        adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, sizes);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                updateSubtotalAndCoffee();
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });

        milk = (CheckBox) findViewById(R.id.milk);
        cream = (CheckBox) findViewById(R.id.cream);
        syrup = (CheckBox) findViewById(R.id.syrup);
        caramel = (CheckBox) findViewById(R.id.caramel);
        whippedCream = (CheckBox) findViewById(R.id.whippedCream);

        milk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onMilkClick(view);
            }
        });
        cream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { onCreamClick(view);}
        });
        syrup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { onSyrupClick(view);}
        });
        caramel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { onCaramelClick(view);}
        });
        whippedCream.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { onWhippedCreamClick(view);}
        });

        /*
        clickButton = (Button) findViewById(R.id.coffeeOrderButton);
        clickButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OrderActivity.yourOrderArrayList.getOrderArray().add(coffee);
                milk.setChecked(false);
                cream.setChecked(false);
                syrup.setChecked(false);
                caramel.setChecked(false);
                whippedCream.setChecked(false);
                spinner.setSelection(0);
                DecimalFormat d = new DecimalFormat("'$'#,##0.00");
                subtotalText.setText(d.format(0));
            }
        });*/
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        updateSubtotalAndCoffee();
    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    /**
     * Updates the subtotal text field and coffee order based on changes in the add-on checkboxes
     */
    private void updateSubtotalAndCoffee() {
        TextView subtotalText = (TextView) findViewById(R.id.coffeeSubtotal);

        DecimalFormat d = new DecimalFormat("'$'#,##0.00");
        double subTotal = 0;
        String sizeChoice = spinner.getSelectedItem().toString();
        if(sizeChoice.equals("Select a size")) {
            subtotalText.setText(d.format(subTotal));
            return;
        }
        coffee.setSize(sizeChoice);
        subTotal = coffee.itemPrice();
        subtotalText.setText(d.format(subTotal));
    }

    public void onMilkClick(View view){
        if (milk.isChecked()) {
            coffee.addObject("milk");
        } else if (!milk.isChecked()){
            coffee.remove("milk");
        }
        updateSubtotalAndCoffee();
    }

    public void onCreamClick(View view){
        if (cream.isChecked()) {
            coffee.addObject("cream");
        } else if (!cream.isChecked()){
            coffee.remove("cream");
        }
        updateSubtotalAndCoffee();
    }

    public void onCaramelClick(View view){
        if (caramel.isChecked()) {
            coffee.addObject("caramel");
        } else if (!caramel.isChecked()){
            coffee.remove("caramel");
        }
        updateSubtotalAndCoffee();
    }

    public void onSyrupClick(View view){
        if (syrup.isChecked()) {
            coffee.addObject("syrup");
        } else if (!syrup.isChecked()){
            coffee.remove("syrup");
        }
        updateSubtotalAndCoffee();
    }

    public void onWhippedCreamClick(View view){
        if (whippedCream.isChecked()) {
            coffee.addObject("whippedCream");
        } else if (!whippedCream.isChecked()){
            coffee.remove("whippedCream");
        }
        updateSubtotalAndCoffee();
    }
}