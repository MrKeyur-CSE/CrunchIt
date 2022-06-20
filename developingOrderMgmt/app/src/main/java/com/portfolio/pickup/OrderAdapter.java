package com.portfolio.pickup;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class OrderAdapter extends RecyclerView.Adapter<com.portfolio.pickup.OrderAdapter.InventoryViewHolder> {

    public interface onItemClickListener{
        void onItemClick(int position);
        void onButtonOneClick(int position);
        void onButtonTwoClick(int position);
        void onButtonThreeClick(int position);
    }

    private ArrayList<Order> mItemList;

    public void setOnItemClickListener(onItemClickListener listener){
        mListener = listener;
    }

    public void setDeliveredButtonClickListener(onItemClickListener listener) {
        listenerDelivery = listener;};

    public void setAcceptedButtonClickListener(onItemClickListener listener) {
        listenerAccepted = listener;};

    public void setCancelledButtonClickListener(onItemClickListener listener) {
        listenerCancelled = listener;};


    private onItemClickListener mListener;

    private onItemClickListener listenerDelivery;

    private onItemClickListener listenerAccepted;

    private onItemClickListener listenerCancelled;


    public static class InventoryViewHolder extends RecyclerView.ViewHolder{

        public TextView itemName_viewHolder;
        public TextView itemCost_viewHolder;
        public ImageView itemThumbnail_viewHolder;
        public ImageButton deliveredButton_viewHolder;
        public ImageButton acceptButton_viewHolder;
        public ImageButton cancelledButton_viewHolder;

        public InventoryViewHolder(@NonNull View itemView, final onItemClickListener listener) {
            super(itemView);
            itemName_viewHolder = itemView.findViewById(R.id.customerName);
            itemCost_viewHolder = itemView.findViewById(R.id.customerAddress);
            itemThumbnail_viewHolder = itemView.findViewById(R.id.customerImage);
            deliveredButton_viewHolder = itemView.findViewById(R.id.orderDeliveredButton);
            acceptButton_viewHolder = itemView.findViewById(R.id.acceptOrderButton);
            cancelledButton_viewHolder = itemView.findViewById(R.id.declineOrderButton);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onItemClick(position);
                        }
                    }
                }
            });

            deliveredButton_viewHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onButtonOneClick(position);

                        }
                    }
                }
            });

            acceptButton_viewHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onButtonTwoClick(position);
                        }
                    }
                }
            });

            cancelledButton_viewHolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null){
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION){
                            listener.onButtonThreeClick(position);
                        }
                    }
                }
            });

        }
    }

    public OrderAdapter(ArrayList<Order> inventoryList) {
        mItemList = inventoryList;
    }

    @NonNull
    @Override
    public InventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_card_fragment, parent, false);
        InventoryViewHolder invHolder = new InventoryViewHolder(v, mListener);
        return invHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryViewHolder holder, int position) {
        Order currentItem = mItemList.get(position);

        holder.itemName_viewHolder.setText(currentItem.getOrderId());
        holder.itemCost_viewHolder.setText(currentItem.getStatus());

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }
}