package com.lau.spring2022.groceteria_app.Activities.Activities.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lau.spring2022.groceteria_app.Activities.Activities.Domains.ProductDomain;
import com.lau.spring2022.groceteria_app.Activities.Activities.Helper.ManagementCart;
import com.lau.spring2022.groceteria_app.Activities.Activities.Interface.ChangeNumberItemsListener;
import com.lau.spring2022.groceteria_app.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    // creating a variable for array list, managementCart and ChangeNumberItemsListener

    ArrayList<ProductDomain> productDomains; // array list of the items added in the class cart

    private ManagementCart managementCart;
    private ChangeNumberItemsListener changeNumberItemsListener;

    // creating a constructor for our variables
    public CartAdapter(ArrayList<ProductDomain> productDomains, Context context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.productDomains = productDomains;
        this.managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    // inflate our layout
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart, parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull CartAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

        // setting data to our views of the recycler view
        holder.title.setText(productDomains.get(position).getName());
        holder.priceEachItem.setText(String.valueOf(productDomains.get(position).getPrice()));
        holder.totalEachItem.setText(String.valueOf(Math.round((productDomains.get(position).getNumberInCart() * productDomains.get(position).getPrice()) * 100) / 100));
        holder.numItems.setText(String.valueOf(productDomains.get(position).getNumberInCart()));

        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(productDomains.get(position).getPic(), "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.pic);

        // when the add button is clicked the number of items will be incremented
        holder.plusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.plusNumberProduct(productDomains, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });

        // when the minus button is clicked the number of items will be decremented
        holder.minusItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.minusNumberProduct(productDomains, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });
    }

    // returning the size of array list
    @Override
    public int getItemCount() {
        return productDomains.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our views
        TextView title;
        TextView priceEachItem;
        ImageView pic;
        ImageView plusItem;
        ImageView minusItem;
        TextView totalEachItem;
        TextView numItems;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // initializing our views with their ids
            title = itemView.findViewById(R.id.titleTxt);
            priceEachItem = itemView.findViewById(R.id.priceEachItem);
            pic = itemView.findViewById(R.id.imageCart);
            minusItem = itemView.findViewById(R.id.minusCartBtn);
            plusItem = itemView.findViewById(R.id.addCartBtn);
            totalEachItem = itemView.findViewById(R.id.totalEachItem);
            numItems = itemView.findViewById(R.id.numberItemTxt);
        }
    }
}
