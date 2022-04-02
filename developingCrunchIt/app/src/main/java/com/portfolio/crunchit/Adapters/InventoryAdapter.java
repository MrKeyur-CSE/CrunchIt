package com.portfolio.crunchit.Adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.portfolio.crunchit.Abstract.Item;
import com.portfolio.crunchit.R;

import java.util.ArrayList;

public class InventoryAdapter extends RecyclerView.Adapter<InventoryAdapter.InventoryViewHolder> {

    public interface onItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(onItemClickListener listener){
        mListener = listener;
    }

    private ArrayList<Item> mItemList;

    private onItemClickListener mListener;

    public static class InventoryViewHolder extends RecyclerView.ViewHolder{

        public TextView itemName_viewHolder;
        public TextView itemCost_viewHolder;
        public ImageView itemThumbnail_viewHolder;
        public InventoryViewHolder(@NonNull View itemView, final onItemClickListener listener) {
            super(itemView);
            itemName_viewHolder = itemView.findViewById(R.id.itemTitle);
            itemCost_viewHolder = itemView.findViewById(R.id.itemCost);
            itemThumbnail_viewHolder = itemView.findViewById(R.id.itemImage);

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

        }
    }

    public InventoryAdapter(ArrayList<Item> inventoryList) {
        mItemList = inventoryList;
    }

    @NonNull
    @Override
    public InventoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.fragment_list_item, parent, false);
        InventoryViewHolder invHolder = new InventoryViewHolder(v, mListener);
        return invHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull InventoryViewHolder holder, int position) {
        Item currentItem = mItemList.get(position);

        holder.itemName_viewHolder.setText(currentItem.getItemName());
        holder.itemCost_viewHolder.setText(currentItem.getItemCost());
        holder.itemThumbnail_viewHolder.setImageBitmap(currentItem.getItemThumbnail());

    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }
}