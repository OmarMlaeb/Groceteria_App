package com.lau.spring2022.groceteria_app.Activities.Activities.Adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lau.spring2022.groceteria_app.Activities.Activities.Domains.ProductDomain;
import com.lau.spring2022.groceteria_app.Activities.Activities.ShowDetailsActivity;
import com.lau.spring2022.groceteria_app.R;

import java.util.ArrayList;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    ArrayList<ProductDomain> productDomains; // array list of the the class category

    public ProductsAdapter(ArrayList<ProductDomain> productDomains) { // constructor
        this.productDomains = productDomains;
    }

    @NonNull
    @Override
    // describes an item view and metadata about its place within the RecyclerView
    public ProductsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate our layout
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_product, parent,false);
        return new ProductsAdapter.ViewHolder(inflate);
    }

    @Override
    // to update the RecyclerView of the category in the main xml
    public void onBindViewHolder(@NonNull ProductsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        // setting data to our views of the recycler view and a clicker listener when the add button of add product is clicked to go to the show details activity
        holder.productName.setText(productDomains.get(position).getName());
        holder.price.setText(String.valueOf(productDomains.get(position).getPrice()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(productDomains.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.productPic);

        holder.addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(), ShowDetailsActivity.class);
                intent.putExtra("object", productDomains.get(position));
                holder.itemView.getContext().startActivity(intent);
            }
        });
    }

    // returning the size of array list
    @Override
    public int getItemCount() {
        return productDomains.size();
    }

    // ViewHolder contents with the item at the given position and also sets up some private fields to be used by RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our views
        TextView productName, price;
        ImageView productPic;
        TextView addButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // initializing our views with their ids
            productName = itemView.findViewById(R.id.titleTxt);
            productPic = itemView.findViewById(R.id.imageProduct);
            price = itemView.findViewById(R.id.priceEachItem);
            addButton = itemView.findViewById(R.id.addButton);
        }
    }
}
