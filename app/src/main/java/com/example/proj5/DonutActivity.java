package com.example.proj5;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DonutActivity extends AppCompatActivity {
    private ArrayList<String> donuts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donut);
        Intent intent = getIntent();

        initializeFlavorList();
        RecyclerView rcview = findViewById(R.id.recyclerView);
        DonutAdapter adapter = new DonutAdapter(this, donuts); //create the adapter
        rcview.setAdapter(adapter); //bind the list of items to the RecyclerView
        //use the LinearLayout for the RecyclerView
        rcview.setLayoutManager(new LinearLayoutManager(this));

    }

    private void initializeFlavorList() {
        donuts.add("Red Velvet");
        donuts.add("Blueberry Chiffon");
        donuts.add("Rasberry Jam Swirl");
        donuts.add("Yas");
        donuts.add("Slay");
        donuts.add("Pur");
        donuts.add("Fungi");
        donuts.add("E coli");
        donuts.add("Salmonella");
        //add three more flavors
        //also DEFINE THESE IN STRINGXML
    }


}
