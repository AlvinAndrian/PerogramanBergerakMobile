package com.example.persona.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.persona.R;
import com.example.persona.adapter.ListAdapter;
import com.example.persona.data.ListData;
import com.example.persona.model.ListModel;

import java.util.ArrayList;

public class ListFragment extends Fragment {
    private RecyclerView rvList;
    private ArrayList<ListModel> listCatatan = new ArrayList<>();

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        rvList = view.findViewById(R.id.listFragment);
        rvList.setHasFixedSize(true);

        listCatatan.addAll(ListData.getlistData());
        showRecyclerList();

        return view;
    }

    private void showRecyclerList() {
        rvList.setLayoutManager(new LinearLayoutManager(this.getContext()));
        ListAdapter listAdapter = new ListAdapter(listCatatan, requireContext());
        listAdapter.setListCatatan(listCatatan);
        rvList.setAdapter(listAdapter);
    }
}