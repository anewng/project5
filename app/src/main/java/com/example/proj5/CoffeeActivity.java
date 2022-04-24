package com.example.proj5;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DecimalFormat;

/**
 The CoffeeActivity class dictates the function of the coffee order activity GUI.
 @author Annie Wang, Jasmine Flanders
 */
public class CoffeeActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private String[] sizes = {"Select a size", "Short", "Tall", "Grande", "Venti"};
    private ArrayAdapter<String> adapter;
    private Coffee coffee = new Coffee();
    private CheckBox milk, cream, syrup, caramel, whippedCream;
    private Button clickButton;
    private TextView subtotalText;

    private View.OnClickListener milkOnClickListener = new View.OnClickListener() {
        /**
         Anonymous inner class to implement the milkOnClickListener method to register the listener
         @param view the current view that is being clicked
         */
        @Override
        public void onClick(View view) {
            onMilkClick(view);
        }
    };
    private View.OnClickListener creamOnClickListener = new View.OnClickListener() {
        /**
         Anonymous inner class to implement the creamOnClickListener method to register the listener
         @param view the current view that is being clicked
         */
        @Override
        public void onClick(View view) {
            onCreamClick(view);
        }
    };
    private View.OnClickListener syrupOnClickListener = new View.OnClickListener() {
        /**
         Anonymous inner class to implement the syrupOnClickListener method to register the listener
         @param view the current view that is being clicked
         */
        @Override
        public void onClick(View view) {
            onSyrupClick(view);
        }
    };
    private View.OnClickListener caramelOnClickListener = new View.OnClickListener() {
        /**
         Anonymous inner class to implement the caramelOnClickListener method to register the listener
         @param view the current view that is being clicked
         */
        @Override
        public void onClick(View view) {
            onCaramelClick(view);
        }
    };
    private View.OnClickListener whippedCreamOnClickListener = new View.OnClickListener() {
        /**
         Anonymous inner class to implement the whippedCreamOnClickListener method to register
         the listener
         @param view the current view that is being clicked
         */
        @Override
        public void onClick(View view) {
            onWhippedCreamClick(view);
        }
    };
    private View.OnClickListener clickButtonOnClickListener = new View.OnClickListener() {
        /**
         Anonymous inner class to implement the clickButtonOnClickListener method to
         register the listener
         @param view the current view that is being clicked
         */
        @Override
        public void onClick(View view) {
            if(spinner.getSelectedItemPosition() == 0) return;

            coffee.setQuantity(1);
            OrderActivity.yourOrderArrayList.getOrderArray().add(coffee);
            milk.setChecked(false);
            cream.setChecked(false);
            syrup.setChecked(false);
            caramel.setChecked(false);
            whippedCream.setChecked(false);
            spinner.setSelection(0);
            DecimalFormat d = new DecimalFormat("'$'#,##0.00");
            subtotalText.setText(d.format(0));

            Context context = getApplicationContext();
            CharSequence text = "Coffee added to order";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();

            finish();
        }
    };
    private AdapterView.OnItemSelectedListener spinnerOnClickListener = new AdapterView.OnItemSelectedListener() {
        /**
         Callback method to be invoked when an item in this view has been selected.
         @param adapterView The AdapterView where the selection happened
         @param view The view within the AdapterView that was clicked
         @param i The position of the view in the adapter
         @param l The row id of the item that is selected
         */
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            updateSubtotalAndCoffee();
        }
        /**
         Callback method to be invoked when the selection disappears from this view.
         @param adapterView The AdapterView that now contains no selected item.
         */
        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    };

    /**
     The onCreate method configures preliminary settings to clarify GUI interactions.
     @param savedInstanceState the Bundle object that stores information on the previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coffee);
        Intent intent = getIntent();
        spinner = findViewById(R.id.sizeSpinner);
        adapter = new ArrayAdapter<String>(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, sizes);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(spinnerOnClickListener);

        milk = (CheckBox) findViewById(R.id.milk);
        cream = (CheckBox) findViewById(R.id.cream);
        syrup = (CheckBox) findViewById(R.id.syrup);
        caramel = (CheckBox) findViewById(R.id.caramel);
        whippedCream = (CheckBox) findViewById(R.id.whippedCream);

        milk.setOnClickListener(milkOnClickListener);
        cream.setOnClickListener(creamOnClickListener);
        syrup.setOnClickListener(syrupOnClickListener);
        caramel.setOnClickListener(caramelOnClickListener);
        whippedCream.setOnClickListener(whippedCreamOnClickListener);

        clickButton = (Button) findViewById(R.id.coffeeOrderButton);
        clickButton.setOnClickListener(clickButtonOnClickListener);
    }

    /**
     Performs necessary actions when the item is selected in the spinner
     @param parent The AdapterView where the selection happened
     @param view The view within the AdapterView that was clicked
     @param position The position of the view in the adapter
     @param id The row id of the item that is selected
     */
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        updateSubtotalAndCoffee();
    }
    /**
     Callback method to be invoked when the selection disappears from this view.
     @param parent The AdapterView that now contains no selected item.
     */
    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    /**
     * Updates the subtotal text field and coffee order based on changes in the add-on checkboxes
     */
    private void updateSubtotalAndCoffee() {
        subtotalText = (TextView) findViewById(R.id.coffeeSubtotal);

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

    /**
     * Updates the coffee object based on the modifications to milk CheckBox
     * @param view the current view that is being clicked
     */
    public void onMilkClick(View view){
        if (milk.isChecked()) {
            coffee.addObject("milk");
        } else if (!milk.isChecked()){
            coffee.remove("milk");
        }
        updateSubtotalAndCoffee();
    }

    /**
     * Updates the coffee object based on the modifications to cream CheckBox
     * @param view the current view that is being clicked
     */
    public void onCreamClick(View view){
        if (cream.isChecked()) {
            coffee.addObject("cream");
        } else if (!cream.isChecked()){
            coffee.remove("cream");
        }
        updateSubtotalAndCoffee();
    }

    /**
     * Updates the coffee object based on the modifications to caramel CheckBox
     * @param view the current view that is being clicked
     */
    public void onCaramelClick(View view){
        if (caramel.isChecked()) {
            coffee.addObject("caramel");
        } else if (!caramel.isChecked()){
            coffee.remove("caramel");
        }
        updateSubtotalAndCoffee();
    }

    /**
     * Updates the coffee object based on the modifications to syrup CheckBox
     * @param view the current view that is being clicked
     */
    public void onSyrupClick(View view){
        if (syrup.isChecked()) {
            coffee.addObject("syrup");
        } else if (!syrup.isChecked()){
            coffee.remove("syrup");
        }
        updateSubtotalAndCoffee();
    }

    /**
     * Updates the coffee object based on the modifications to whipped cream CheckBox
     * @param view the current view that is being clicked
     */
    public void onWhippedCreamClick(View view){
        if (whippedCream.isChecked()) {
            coffee.addObject("whippedCream");
        } else if (!whippedCream.isChecked()){
            coffee.remove("whippedCream");
        }
        updateSubtotalAndCoffee();
    }
}