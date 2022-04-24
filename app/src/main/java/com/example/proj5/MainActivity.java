package com.example.proj5;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import com.example.proj5.databinding.ActivityMainBinding;

/**
 The MainActivity class dictates the function of the main home screen in the GUI.
 @author Annie Wang, Jasmine Flanders
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private View.OnClickListener donutOnClickListener = new View.OnClickListener() {
        /**
         Anonymous inner class to implement the showDonutActivity method to register the listener
         @param view the current view that is being clicked
         */
        @Override
        public void onClick(View view) {
            showDonutActivity(view);
        }
    };
    private View.OnClickListener coffeeOnClickListener = new View.OnClickListener() {
        /**
         Anonymous inner class to implement the showDonutActivity method to register the listener
         @param view the current view that is being clicked
         */
        @Override
        public void onClick(View view) {
            showCoffeeActivity(view);
        }
    };
    private View.OnClickListener yourOrderOnClickListener = new View.OnClickListener() {
        /**
         Anonymous inner class to implement the showDonutActivity method to register the listener
         @param view the current view that is being clicked
         */
        @Override
        public void onClick(View view) {
            showOrderActivity(view);
        }
    };
    private View.OnClickListener storeOrdersOnClickListener = new View.OnClickListener() {
        /**
         Anonymous inner class to implement the showDonutActivity method to register the listener
         @param view the current view that is being clicked
         */
        @Override
        public void onClick(View view) {
            showStoreOrderActivity(view);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.donutButton.setOnClickListener(donutOnClickListener);
        binding.coffeeButton.setOnClickListener(coffeeOnClickListener);
        binding.yourOrderButton.setOnClickListener(yourOrderOnClickListener);
        binding.storeOrdersButton.setOnClickListener(storeOrdersOnClickListener);
    }

    public void showDonutActivity(View view) {
        Intent intent = new Intent(this, DonutActivity.class);
        startActivity(intent);
    }

    public void showCoffeeActivity(View view) {
        Intent intent = new Intent(this, CoffeeActivity.class);
        startActivity(intent);
    }

    public void showOrderActivity(View view) {
        Intent intent = new Intent(this, OrderActivity.class);
        startActivity(intent);
    }

    public void showStoreOrderActivity(View view) {
        Intent intent = new Intent(this, StoreOrderActivity.class);
        startActivity(intent);
    }


}