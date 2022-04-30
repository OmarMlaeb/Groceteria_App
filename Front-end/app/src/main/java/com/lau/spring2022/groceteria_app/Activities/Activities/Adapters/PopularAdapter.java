package com.lau.spring2022.groceteria_app.Activities.Activities.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lau.spring2022.groceteria_app.Activities.Activities.Domains.ProductDomain;
import com.lau.spring2022.groceteria_app.R;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {

    ArrayList<ProductDomain> productDomains; // array list of the the class category

    public PopularAdapter(ArrayList<ProductDomain> productDomains) { // constructor
        this.productDomains = productDomains;
    }

    @NonNull
    @Override
    // describes an item view and metadata about its place within the RecyclerView
    public PopularAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_popular, parent,false);
        return new PopularAdapter.ViewHolder(inflate);
    }

    @Override
    // to update the RecyclerView of the category in the main xml
    public void onBindViewHolder(@NonNull PopularAdapter.ViewHolder holder, int position) {
        holder.productName.setText(productDomains.get(position).getName());
        holder.price.setText(String.valueOf(productDomains.get(position).getPrice()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(productDomains.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.productPic);
    }

    @Override
    public int getItemCount() {
        return productDomains.size();
    }

    // ViewHolder contents with the item at the given position and also sets up some private fields to be used by RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView productName, price;
        ImageView productPic;
        TextView addButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            productName = itemView.findViewById(R.id.title);
            productPic = itemView.findViewById(R.id.image);
            price = itemView.findViewById(R.id.price);
            addButton = itemView.findViewById(R.id.addButton);
        }
    }

}
