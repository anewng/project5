package com.example.proj5;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

/**
 The DonutActivity class dictates the function of the general donut selection screen in the GUI.
 @author Annie Wang, Jasmine Flanders
 */
public class DonutActivity extends AppCompatActivity {
    private ArrayList<String> donuts = new ArrayList<>();
    public RecyclerView recyclerView;

    /**
     The onCreate method configures preliminary settings to clarify GUI interactions.
     @param savedInstanceState the Bundle object that stores information on the previous state
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);
        Intent intent = getIntent();

        initializeFlavorList();
        RecyclerView rcview = findViewById(R.id.recyclerView);
        DonutAdapter adapter = new DonutAdapter(this, donuts); //create the adapter
        rcview.setAdapter(adapter); //bind the list of items to the RecyclerView
        rcview.setLayoutManager(new LinearLayoutManager(this));

        recyclerView = this.findViewById(R.id.recyclerView);
    }

    /**
     Adds flavors to the flavor list instance variable.
     */
    private void initializeFlavorList() {
        donuts.add("Red Velvet");
        donuts.add("Blueberry Chiffon");
        donuts.add("Raspberry Jam Swirl");
        donuts.add("Strawberry Shortcake");
        donuts.add("Yas");
        donuts.add("Slay");
        donuts.add("Purr");
        donuts.add("Periodt");
        donuts.add("Fungi");
        donuts.add("E coli");
        donuts.add("Salmonella");
        donuts.add("Yeast Infection");
    }
}
