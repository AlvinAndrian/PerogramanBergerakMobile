package com.example.persona;

import android.view.View;

public class CustomOnItemClickListener implements View.OnClickListener {
    private int position;
    private OnItemClickCallback onItemClickCallback;

    public CustomOnItemClickListener(int position, OnItemClickCallback onItemClickCallBack) {
        this.position = position;
        this.onItemClickCallback = onItemClickCallBack;
    }

    @Override
    public void onClick(View view) {
        onItemClickCallback.onItemClicked(view,position);
    }

    public interface OnItemClickCallback {
        void onItemClicked(View view, int position);
    }
}
