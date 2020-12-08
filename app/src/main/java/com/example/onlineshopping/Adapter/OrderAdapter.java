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

import com.example.onlineshopping.Model.Interfaces.OrderSelected;
import com.example.onlineshopping.Model.Interfaces.ProductSelected;
import com.example.onlineshopping.Model.Order;
import com.example.onlineshopping.Model.Product;
import com.example.onlineshopping.R;

import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {

    ArrayList<Order> orders;
    Context context;
    OrderSelected orderSelected;

    public OrderAdapter(ArrayList<Order> orders, Context context, OrderSelected orderSelected) {
        this.orders = orders;
        this.context = context;
        this.orderSelected = orderSelected;
    }

    public void setOrders(ArrayList<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        CardView itemView = (CardView) layoutInflater.inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {

        holder.from.setText("From user: " + orders.get(position).getCustomerId());
        holder.orderNumber.setText("Order number: " + orders.get(position).getId());
        if (!orders.get(position).isPaid() && !orders.get(position).isPending()) {
            holder.condition.setText("Delivery");
        }
        else if (orders.get(position).isPaid()) {
            holder.condition.setText("Done");
        }
        else {
            holder.condition.setText("Pending");
        }
        holder.price.setText("Total: " + orders.get(position).getPrice());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderSelected.didTapOrder(orders.get(position));

            }
        });
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView from, orderNumber, condition, price;
        ImageView image;

        OrderViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.image);
            from = (TextView) itemView.findViewById(R.id.from);
            orderNumber = (TextView) itemView.findViewById(R.id.orderNumber);
            condition = (TextView) itemView.findViewById(R.id.condition);
            price = (TextView) itemView.findViewById(R.id.price);

        }
    }
}