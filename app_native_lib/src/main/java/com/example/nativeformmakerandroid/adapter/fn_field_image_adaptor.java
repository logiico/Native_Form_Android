package com.example.nativeformmakerandroid.adapter;


import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import com.example.nativeformmakerandroid.MyApplication;
import com.example.nativeformmakerandroid.R;
import com.example.nativeformmakerandroid.ActivityDisplayImage;
import com.example.nativeformmakerandroid.helper.LogHelper;
import com.example.nativeformmakerandroid.model.NativeForm.FileModelJavaScript;

public class fn_field_image_adaptor extends RecyclerView.Adapter<fn_field_image_adaptor.ViewHolder> {

    //All methods in this adapter are required for a bare minimum recyclerview adapter
    private int listItemLayout;
    ArrayList<FileModelJavaScript> itemList;

    // Constructor of the class
    public fn_field_image_adaptor(int layoutId, ArrayList<FileModelJavaScript> itemList) {
        listItemLayout = layoutId;
        this.itemList = itemList;
    }

    // get the size of the list
    @Override
    public int getItemCount() {
        return itemList == null ? 0 : itemList.size();
    }


    // specify the row layout file and click for each row
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(listItemLayout, parent, false);
        ViewHolder myViewHolder = new ViewHolder(view);
        return myViewHolder;
    }

    // load data in each row element
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int listPosition) {
        TextView item = holder.item;
        item.setText(itemList.get(listPosition).Title);
        item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MyApplication.getInstance(), ActivityDisplayImage.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("mode", "storage");
                String s = itemList.get(listPosition).filePaths != null ? itemList.get(listPosition).filePaths.get(0) : "";//(new File(itemList.get(listPosition).Title).getPath());
                LogHelper.d("address_path", s);
                intent.putExtra("address", s);
                MyApplication.getInstance().startActivity(intent);
            }
        });
    }

    // Static inner class to initialize the views of rows
    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView item;

        public ViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            item = (TextView) itemView.findViewById(R.id.row_item);


        }

        @Override
        public void onClick(View view) {
            LogHelper.d("onclick", "onClick " + getLayoutPosition() + " " + item.getText());
        }
    }
}
