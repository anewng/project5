package com.example.proj5;

import android.content.Context;
import android.content.Intent;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

/**
 The Donut Adapter class is used to adapt Donut objects for a Recycler View.
 It defines the necessary methods to create a Donut Holder Object/contains the DonutHolder class.
 @author Annie Wang, Jasmine Flanders
 */
class DonutAdapter extends RecyclerView.Adapter<DonutAdapter.DonutHolder>{
    private Context context;
    private ArrayList<String> donuts;

    /**
     Constructor creates a Donut Adapter object.
     @param context the application context
     @param donuts the arraylist of donut flavors to be adapted
     */
    public DonutAdapter(Context context, ArrayList<String> donuts) {
        this.context = context;
        this.donuts = donuts;
    }

    /**
     * This method will inflate the row layout for the items in the RecyclerView
     * @param parent
     * @param viewType
     * @return DonutHolder view
     */
    @NonNull
    @Override
    public DonutHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.donut_layout, parent, false);

        return new DonutHolder(view);
    }

    /**
     * Assign data values for each row according to their "position" (index) when the item becomes
     * visible on the screen.
     * @param holder the instance of ItemsHolder
     * @param position the index of the item in the list of items
     */
    @Override
    public void onBindViewHolder(@NonNull DonutHolder holder, int position) {
        holder.flavor.setText(donuts.get(position));
    }

    /**
     * Get the number of donuts in the ArrayList.
     * @return the number of donuts in the list.
     */
    @Override
    public int getItemCount() {
        return donuts.size();
    }

    /**
     The DonutHolder inner class is used to create/manipulate DonutHolder objects for a Recycler View.
     @author Annie Wang, Jasmine Flanders
     */
    public static class DonutHolder extends RecyclerView.ViewHolder {
        private TextView flavor;
        private ConstraintLayout parentLayout;

        /**
         Constructor creates a DonutHolder object.
         @param itemView the view being used.
         */
        public DonutHolder(@NonNull View itemView) {
            super(itemView);
            flavor = itemView.findViewById(R.id.donutFlavor);
            parentLayout = itemView.findViewById(R.id.rowLayout);
            parentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(itemView.getContext(), DonutSelectedActivity.class);
                    intent.putExtra("ITEM", flavor.getText());
                    itemView.getContext().startActivity(intent);
                }
            });
        }


        }
    }
