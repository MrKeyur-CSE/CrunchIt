package com.portfolio.crunchit.Adapters;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.graphics.drawable.IconCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.portfolio.crunchit.Abstract.Item;
import com.portfolio.crunchit.Fragments.ListItem;
import com.portfolio.crunchit.R;

import java.util.ArrayList;
import java.util.List;

public class GridLayoutAdapter extends ArrayAdapter {
    public GridLayoutAdapter(@NonNull Context context, ArrayList<Item> itemList) {
        super(context, 0, itemList);

        mItemList = itemList;
    }
    private ArrayList<Item> mItemList;




    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listitemView = convertView;
        if (listitemView == null) {
            // Layout Inflater inflates each item to be displayed in GridView.
            listitemView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_list_item, parent, false);
        }
        Item item = mItemList.get(position);
        TextView itemName = listitemView.findViewById(R.id.itemTitle);
        ImageView itemThumbs = listitemView.findViewById(R.id.itemImage);
        itemName.setText(item.getItemName());
        itemThumbs.setImageBitmap(BitmapFactory.decodeResource(getContext().getResources(), R.drawable.loginimg));
        return listitemView;
    }



}
