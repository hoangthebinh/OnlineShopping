package com.example.onlineshopping.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.onlineshopping.Model.Interfaces.ProductSelected;
import com.example.onlineshopping.Model.Product;
import com.example.onlineshopping.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    ArrayList<Product> products;
    Context context;
    ProductSelected productSelected;

    public ProductAdapter(ArrayList<Product> products, Context context, ProductSelected productSelected) {
        this.products = products;
        this.context = context;
        this.productSelected = productSelected;
    }

    public ProductAdapter(ArrayList<Product> products, Context context) {
        this.products = products;
        this.context = context;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CardView itemView = (CardView) layoutInflater.inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {

        holder.name.setText(products.get(position).getName());
        holder.type.setText(products.get(position).getKind());
        holder.group.setText(products.get(position).getGroup());

        if (products.get(position).getGroup().equals("Electrical"))
            holder.image.setImageResource(R.drawable.smartphone);
        else if (products.get(position).getGroup().equals("Machine"))
            holder.image.setImageResource(R.drawable.refrigerator);
        else if (products.get(position).getGroup().equals("Furniture"))
            holder.image.setImageResource(R.drawable.sofa);
        else if (products.get(position).getGroup().equals("Food"))
            holder.image.setImageResource(R.drawable.fruits);
        else if (products.get(position).getGroup().equals("Clothing"))
            holder.image.setImageResource(R.drawable.clothing);

        holder.price.setText("" + products.get(position).getPrice());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (productSelected != null)
                    productSelected.didTapProduct(products.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView name, type, group, price;
        ImageView image;

        ProductViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            name = (TextView) itemView.findViewById(R.id.name);
            type = (TextView) itemView.findViewById(R.id.type);
            group = (TextView) itemView.findViewById(R.id.group);
            price = (TextView) itemView.findViewById(R.id.price);

         }
    }
}