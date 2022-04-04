package com.example.persona.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.persona.R;
import com.example.persona.activity.DetailCatatanActivity;
import com.example.persona.model.ListModel;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {
    private Context context;
    private ArrayList<ListModel> listCatatan;

    public ArrayList<ListModel> getListCatatan(){
        return listCatatan;
    }
    public void setListCatatan(ArrayList<ListModel> listCatatan){
        this.listCatatan = listCatatan;
    }
    public ListAdapter(ArrayList<ListModel> listCatatan, Context context){
        this.listCatatan = listCatatan;
        this.context = context;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_cardview_list, viewGroup, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder (@NonNull final ListViewHolder holder, int position) {
        ListModel list = listCatatan.get(position);

        holder.tvName.setText (list.getName());
        holder.tvTanggal.setText (list.getTanggal());
    }

    @Override
    public int getItemCount() {
        return listCatatan.size();
    }

    class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView tvName;
        TextView tvTanggal;

        ListViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tv_item_name_list);
            tvTanggal = view.findViewById(R.id.tv_item_tanggallist);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            ListModel list = getListCatatan().get(position);
            Intent intent = new Intent (context, DetailCatatanActivity.class);
            intent.putExtra(DetailCatatanActivity.ITEM_EXTRA,list);
            context.startActivity(intent);
        }
    }
}
