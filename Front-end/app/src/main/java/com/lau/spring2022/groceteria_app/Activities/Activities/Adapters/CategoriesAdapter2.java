package com.lau.spring2022.groceteria_app.Activities.Activities.Adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.lau.spring2022.groceteria_app.Activities.Activities.BeveragesActivity;
import com.lau.spring2022.groceteria_app.Activities.Activities.Domains.CategoryDomain;
import com.lau.spring2022.groceteria_app.Activities.Activities.FreshFoodActivity;
import com.lau.spring2022.groceteria_app.Activities.Activities.FrozenFoodActivity;
import com.lau.spring2022.groceteria_app.Activities.Activities.ElectronicsActivity;
import com.lau.spring2022.groceteria_app.Activities.Activities.Vegetables_FruitsActivity;
import com.lau.spring2022.groceteria_app.R;

import java.util.ArrayList;

public class CategoriesAdapter2 extends RecyclerView.Adapter<CategoriesAdapter2.ViewHolder> {

    // creating a variable for array list

    ArrayList<CategoryDomain> categoryDomains; // array list of the the class category

    // creating a constructor for our variables
    public CategoriesAdapter2(ArrayList<CategoryDomain> categoryDomains) {
        this.categoryDomains = categoryDomains;
    }

    @NonNull
    @Override
    // describes an item view and metadata about its place within the RecyclerView
    public CategoriesAdapter2.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // inflate our layout
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_category2, parent,false);
        return new CategoriesAdapter2.ViewHolder(inflate);
    }

    @Override
    // to update the RecyclerView of the category in the main xml
    public void onBindViewHolder(@NonNull CategoriesAdapter2.ViewHolder holder, int position) {

        // setting data to our views of the recycler view and a clicker listener when a category is clicked to go to its corresponding activity
        holder.categoryName.setText(categoryDomains.get(position).getCategory_name());
        String picURL = "";
        switch (position){
            case 0:{
                picURL = "freshfood_icon";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_background));
                holder.mainLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(holder.itemView.getContext(), FreshFoodActivity.class);
                        holder.itemView.getContext().startActivity(intent);
                    }
                });
                break;
            } case 1:{
                picURL = "frozenfood_icon";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_background));
                holder.mainLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(holder.itemView.getContext(), FrozenFoodActivity.class);
                        holder.itemView.getContext().startActivity(intent);
                    }
                });
                break;
            } case 2:{
                picURL = "beverages_icon";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_background));
                holder.mainLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(holder.itemView.getContext(), BeveragesActivity.class);
                        holder.itemView.getContext().startActivity(intent);
                    }
                });
                break;
            } case 3:{
                picURL = "electronics_icon";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_background));
                holder.mainLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(holder.itemView.getContext(), ElectronicsActivity.class);
                        holder.itemView.getContext().startActivity(intent);
                    }
                });
                break;
            }
            case 4:{
                picURL = "vegetables_icon";
                holder.mainLayout.setBackground(ContextCompat.getDrawable(holder.itemView.getContext(),R.drawable.category_background));
                holder.mainLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(holder.itemView.getContext(), Vegetables_FruitsActivity.class);
                        holder.itemView.getContext().startActivity(intent);
                    }
                });
                break;
            }
        }
        int drawableResourceId = holder.itemView.getContext().getResources().getIdentifier(picURL, "drawable", holder.itemView.getContext().getPackageName());

        Glide.with(holder.itemView.getContext()).load(drawableResourceId).into(holder.categoryPic);
    }

    // returning the size of array list
    @Override
    public int getItemCount() {
        return categoryDomains.size();
    }

    // ViewHolder contents with the item at the given position and also sets up some private fields to be used by RecyclerView
    public class ViewHolder extends RecyclerView.ViewHolder {

        // creating variables for our views
        TextView categoryName;
        ImageView categoryPic;
        ConstraintLayout mainLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // initializing our views with their ids
            categoryName = itemView.findViewById(R.id.categoryName);
            categoryPic = itemView.findViewById(R.id.categoryPic);
            mainLayout = itemView.findViewById(R.id.mainLayout);
        }
    }
}
