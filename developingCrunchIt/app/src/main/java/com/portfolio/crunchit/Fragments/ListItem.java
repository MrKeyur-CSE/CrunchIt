package com.portfolio.crunchit.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.portfolio.crunchit.R;

import java.net.URI;

public class ListItem extends Fragment {

    private static final String Name = "Item Name";
    private static final String Cost = "Cost";
    public ListItem() {
        // Required empty public constructor
    }

    public static ListItem newInstance(String Name, String Quantity, String Location, String SpecialNotes, URI itemImage) {
        ListItem fragment = new ListItem();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_list_item, container, false);

    }
}